package com.cq.cd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("posts")
public class Post {
	@TableField("postId")
	@TableId(type = IdType.AUTO)
	private Integer postId;
	private Integer userId;
	private Integer plateId;
	private String title;
	private String postContent;
	private LocalDateTime postCreatedData;
	private LocalDateTime lastEditData;
	private Integer clickNumber;
	private Integer commentNumber;
	private Integer postLikeNumber;
}
