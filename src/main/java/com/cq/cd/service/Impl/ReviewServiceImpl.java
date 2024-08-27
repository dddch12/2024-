package com.cq.cd.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.entity.Review;
import com.cq.cd.mapper.ReviewMapper;
import com.cq.cd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public List<Review> getbypostid(Integer postId) {
		return reviewMapper.getbypostid(postId);
	}

	@Override
	public Boolean decrementCommentLikes(Integer commentId) {
		int res = reviewMapper.decrementCommentLikes(commentId);
		if (res == 1) return true;
		else return false;
	}

	@Override
	public Boolean incrementCommentLikes(Integer commentId) {
		int res = reviewMapper.incrementCommentLikes(commentId);
		if (res == 1) return true;
		else return false;
	}

	@Override
	public List<Review> selectReviewsByKeyword(String keyword) {
		return reviewMapper.selectReviewsByKeyword(keyword);
	}

	@Override
	public List<Review> selectReviewsByBoard(Integer boardId) {
		return reviewMapper.selectReviewsByBoard(boardId);
	}
}
