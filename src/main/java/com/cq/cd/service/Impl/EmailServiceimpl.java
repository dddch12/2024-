package com.cq.cd.service.Impl;

import com.cq.cd.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;

@Service
public class EmailServiceimpl implements EmailService {
	@Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendMessageToEmail(String verifyCode, String email) {
        Context context = new Context(); // 引入Template的Context
        // 设置模板中的变量（分割验证码）
        context.setVariable("verifyCode", Arrays.asList(verifyCode.split("")));
        // 第一个参数为模板的名称(html不用写全路径)
        String process = templateEngine.process("EmailVerificationCode.html", context); // 这里不用写全路径
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("【POLAR】验证码"); // 邮件的标题
            helper.setFrom(username); // 发送者
            helper.setTo(email); // 接收者
            helper.setSentDate(new Date()); // 时间
            helper.setText(process, true); // 第二个参数true表示这是一个html文本
        } catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		javaMailSender.send(mimeMessage);
    }
}
