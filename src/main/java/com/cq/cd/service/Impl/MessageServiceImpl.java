package com.cq.cd.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.entity.Message;
import com.cq.cd.entity.User;
import com.cq.cd.mapper.MessageMapper;
import com.cq.cd.service.MessageService;
import com.cq.cd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private UserService userService;

	@Override
	public List<User> findSenderByUserId(Integer userId) {
		List<Integer> list = messageMapper.findSenderByUserId(userId);
		List<User> userList = new ArrayList<>();
		for (Integer senderID : list) {
			User user = userService.getById(senderID);
			userList.add(user);
		}
		return userList;
	}

	@Override
	public List<Message> findAllMessagesWithUser(Integer userId, Integer senderId) {
		return messageMapper.findAllMessagesWithUser(userId, senderId);
	}
}
