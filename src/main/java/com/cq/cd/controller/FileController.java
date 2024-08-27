package com.cq.cd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//status = 500 后端异常
@RestController
@CrossOrigin
public class FileController {
	private static final String UPLOAD_FOLDER = "D:\\mavenProject\\mavenproject820\\src\\main\\resources\\static\\upload";
	@Value("${file.uploadurl}")
	public String path;

	/**
	 * 上传图片功能
	 *
	 * @param file 上传文件
	 * @return 返回文件名称，需要再调用相应更新接口
	 * @throws IOException
	 */
	@PostMapping("/uploadfile")
	public String test(@RequestParam(required = true) MultipartFile file, HttpServletRequest request) throws IOException {
		//获取文件名
		String oldName = file.getOriginalFilename();
		String suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
		String newName = UUID.randomUUID().toString() + "." + suffix;
		System.out.println(newName);
		File file1 = new File(path, newName);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		// 保存文件
		file.transferTo(file1);
		return newName;
	}
}
