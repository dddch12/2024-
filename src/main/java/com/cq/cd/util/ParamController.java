package com.cq.cd.util;

import com.cq.cd.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamController {
	@RequestMapping("/getTest1")
	public String getTest1(String nickname, String phone){
		System.out.println("nickname: " + nickname);
		System.out.println("phone: " + phone);
		return "Get请求";
	}
	//参数映射
	@RequestMapping(value = "/postTest1",method = RequestMethod.POST)
	public String postTest1(@RequestParam(value = "nickname", required = false) String name, String phone){
		return "post请求";
	}
	@RequestMapping(value = "/postTest2", method = RequestMethod.POST)
	public String postTest2(String username, String password){
		System.out.println("nickname: " + username);
		System.out.println("phone: " + password);
		return "post请求";
	}
	@RequestMapping(value = "postTest3", method = RequestMethod.POST)
	public String postTest3(User user){
		System.out.println(user);
		return "post请求";
	}

	//必须以json格式传递
	@RequestMapping(value = "postTest4", method = RequestMethod.POST)
	public String postTest4(@RequestBody User user){
		System.out.println(user);
		return "post请求";
	}
	//通配符
	@GetMapping("/test/**")
	public String test(){
		return "通配符请求";
	}

}
