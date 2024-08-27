package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.cd.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {
	@Select("select * from comments where userId = #{userId}")
	List<Review> findAllById(Integer userId);


	@Select("select * from comments where postsId = #{postId}")
	List<Review> getbypostid(Integer postId);


	@Update("UPDATE comments SET commentLikeNumber = commentLikeNumber - 1 WHERE commentId = #{commentId}")
	int decrementCommentLikes(Integer commentId);

	@Update("UPDATE comments SET commentLikeNumber = commentLikeNumber + 1 WHERE commentId = #{commentId}")
	int incrementCommentLikes(Integer commentId);


	// 根据关键字筛选评论
	@Select("SELECT * FROM reviews WHERE commentContent LIKE CONCAT('%', #{keyword}, '%')")
	List<Review> selectReviewsByKeyword(@Param("keyword") String keyword);

	// 根据板块筛选评论
	@Select("SELECT * FROM reviews WHERE boardId = #{boardId}")
	List<Review> selectReviewsByBoard(@Param("boardId") Integer boardId);

}
