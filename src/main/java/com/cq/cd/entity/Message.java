package com.cq.cd.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("message")
public class Message {
	@TableId
	private Integer messageId;
	private Integer senderId;
	private Integer receiveId;
	private String messageContent;
	private LocalDateTime sendTime;
	private Boolean isRead;
	private String messageSendStatus;

}
