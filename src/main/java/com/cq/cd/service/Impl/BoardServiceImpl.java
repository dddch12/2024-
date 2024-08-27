package com.cq.cd.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.entity.Board;
import com.cq.cd.mapper.BoardMapper;
import com.cq.cd.mapper.PostMapper;
import com.cq.cd.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public Integer countPostsByPlateId(Integer plateId) {
		return boardMapper.countPostsByPlateId(plateId);
	}

	@Autowired
    private PostMapper postMapper;

    @Override
    public Integer countPostsCreatedTodayByPlateId(Integer plateId) {
        return postMapper.countPostsCreatedTodayByPlateId(plateId);
    }

	@Override
	public Board getboardbyName(String boardname) {
		return boardMapper.getboardbyName(boardname);
	}
}
