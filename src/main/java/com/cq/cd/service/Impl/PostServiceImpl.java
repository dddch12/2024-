package com.cq.cd.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.entity.Post;
import com.cq.cd.mapper.PostMapper;
import com.cq.cd.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Override
	public List<Post> getpostbyid(Integer userId) {
		return postMapper.getpostbyid(userId);
	}

	@Override
	public Post getpostbytitle(String postTitle) {
		return postMapper.getpostbytitle(postTitle);
	}


}
