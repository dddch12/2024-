package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.Post;

import java.util.List;

public interface PostService extends IService<Post> {
	List<Post> getpostbyid(Integer userId);

	Post getpostbytitle(String postTitle);

}
