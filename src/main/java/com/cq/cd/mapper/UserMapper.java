package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.cd.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	//根据用户id查询所有评论
	@Select("select * from users where userId = #{userId}")
	@Results({
			@Result(column = "userId", property = "userId"),
			@Result(column = "userId", property = "Reviews",  many = @Many(select = "com.cq.cd.mapper.ReviewMapper.findAllById"))
	})
	User selectAllReviews(Integer userId);

	@Update("update users set passWord = #{passWord} where userName = #{userName}")
	int Updatepwd(User user);


	@Select("Select * from users where userName = #{username}")
	User getuserbyName(String username);

	@Select("select count(*) from users where DATE(userCreatedData)= CURDATE()")
	Integer gettodayUser();
}
