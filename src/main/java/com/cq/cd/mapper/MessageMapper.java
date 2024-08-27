package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.cd.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {


	@Select("SELECT senderId FROM message WHERE receiveId = #{userId}")
	List<Integer> findSenderByUserId(Integer userId);

	@Select("SELECT * FROM message WHERE receiveId = #{userId} AND senderId = #{senderId} ORDER BY timestamp ASC")
	List<Message> findAllMessagesWithUser(Integer userId, Integer senderId);
}
