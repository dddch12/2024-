package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.Message;
import com.cq.cd.entity.User;

import java.util.List;

public interface MessageService extends IService<Message> {


	List<User> findSenderByUserId(Integer userId);


	List<Message> findAllMessagesWithUser(Integer userId, Integer senderId);
}
