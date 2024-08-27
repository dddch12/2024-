package com.cq.cd.controller;


import com.cq.cd.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;
	// 存储验证码的Map，使用ConcurrentHashMap保证线程安全
	private ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();

	// 存储验证码过期时间的Map
	private ConcurrentHashMap<String, Long> expireMap = new ConcurrentHashMap<>();

	// 定时任务清理过期验证码
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public EmailController() {
		// 每分钟清理一次过期验证码
		scheduler.scheduleAtFixedRate(this::clearExpiredCodes, 1, 1, TimeUnit.MINUTES);
	}

	@PostMapping("/code")
	public ResponseEntity<String> sendMessageToEmail(@RequestParam(required = true) String email) {
		if (email == null || email.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("邮箱为空");
		}

		// 校验邮箱格式
		if (!isValidEmail(email)) {
			return ResponseEntity.badRequest().body("邮箱格式错误");
		}

		// 从内存中查看是否已经有该邮箱的验证码
		String verifyCode = codeMap.get(email);
		if (verifyCode != null && !isCodeExpired(email)) {
			return ResponseEntity.status(HttpStatus.OK).body("验证码已发送 => " + verifyCode);
		}

		// 生成新的验证码并发送
		verifyCode = generateSixDigitCode();
		emailService.sendMessageToEmail(verifyCode, email);

		// 存储验证码并设置过期时间（例如5分钟）
		codeMap.put(email, verifyCode);
		expireMap.put(email, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5));

		return ResponseEntity.status(HttpStatus.OK).body("发送成功");
	}

	// 清理过期验证码
	private void clearExpiredCodes() {
		long now = System.currentTimeMillis();
		expireMap.entrySet().removeIf(entry -> entry.getValue() < now);
		codeMap.keySet().removeIf(email -> expireMap.get(email) == null);
	}

	// 判断验证码是否过期
	public boolean isCodeExpired(String email) {
		Long expireTime = expireMap.get(email);
		return expireTime == null || expireTime < System.currentTimeMillis();
	}

	// 校验邮箱格式
	private boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	// 生成六位随机验证码
	private String generateSixDigitCode() {
		Random random = new Random();
		int code = random.nextInt(900000) + 100000; // 生成100000到999999之间的随机数
		return String.valueOf(code);
	}

    public ConcurrentHashMap<String, String> getCodeMap() {
        return codeMap;
    }
}
