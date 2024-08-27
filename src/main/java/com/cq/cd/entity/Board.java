package com.cq.cd.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("plate")
public class Board {
	@TableId
	private Integer plateId;
	private String plateName;
	private String introduction;
	private Integer postsNumber;
	private Integer todayNumber;
}
