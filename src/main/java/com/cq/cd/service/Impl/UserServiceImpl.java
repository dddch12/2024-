package com.cq.cd.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.mapper.UserMapper;
import com.cq.cd.entity.User;
import com.cq.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;


	public static final String permissioncode = "1000";

	@Override
	public User selectAllReviews(Integer userId) {
		return userMapper.selectAllReviews(userId);
	}

	@Override
	public int updatePwd(User user) {
		return userMapper.Updatepwd(user);
	}

	@Override
	public Boolean login(User user) {
		String username = user.getUserName();
		String password = user.getPassWord();
		String email = user.getEmail();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();

		// Check if either username or email is provided
		if (username != null && !username.isEmpty()) {
			queryWrapper.eq("userName", username);
		} else if (email != null && !email.isEmpty()) {
			queryWrapper.eq("email", email);
		} else {
			// Neither username nor email provided
			return false;
		}

		// Add password condition
		queryWrapper.eq("passWord", password);

		// Execute query
		User user1 = userMapper.selectOne(queryWrapper);

		return user1 != null;
	}

	@Override
	public Boolean authlogin(User user) {
		String username = user.getUserName();
		String password = user.getPassWord();
		String email = user.getEmail();

		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		// 根据用户名或邮箱进行查询
		if (username != null && !username.isEmpty()) {
			queryWrapper.eq("userName", username);
		} else if (email != null && !email.isEmpty()) {
			queryWrapper.eq("email", email);
		} else {
			// 没有提供用户名或邮箱
			return false;
		}
		// 添加密码和权限码条件
		queryWrapper.eq("passWord", password)
					.eq("permissionCode", permissioncode);
		// 执行查询
		User user1 = userMapper.selectOne(queryWrapper);
		return user1 != null;
	}

	@Override
	public User getuserbyName(String username) {
		return userMapper.getuserbyName(username);
	}

	@Override
	public Integer gettodayUser() {
		return userMapper.gettodayUser();
	}
}
