package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.Board;

public interface BoardService extends IService<Board> {
	 Integer countPostsByPlateId(Integer plateId);

	 /**
     * 根据板块ID获取今日创建的帖子数量
     * @param plateId 板块ID
     * @return 今日创建的帖子数量
     */
    Integer countPostsCreatedTodayByPlateId(Integer plateId);

	 Board getboardbyName(String boardname);
}
