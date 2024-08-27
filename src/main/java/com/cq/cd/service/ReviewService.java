package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewService extends IService<Review> {


	List<Review> getbypostid(Integer postId);

	Boolean decrementCommentLikes(Integer commentId);

	Boolean incrementCommentLikes(Integer commentId);


	List<Review> selectReviewsByKeyword(@Param("keyword") String keyword);

	// 根据板块筛选评论

	List<Review> selectReviewsByBoard(@Param("boardId") Integer boardId);
}
