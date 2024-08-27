package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.User;


public interface UserService extends IService<User> {

	User selectAllReviews(Integer userId);

	int updatePwd(User user);

	Boolean login(User user);

	Boolean authlogin(User user);

	User getuserbyName(String username);


	Integer gettodayUser();
}
