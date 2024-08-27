package com.cq.cd.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("comments")
public class Review {
	@TableId
	private Integer commentId;
	private Integer postsId;
	private Integer userId;
	private String commentContent;
	private String commentPicture;
	private LocalDateTime commentData;
	private Long commentLikeNumber;
}
