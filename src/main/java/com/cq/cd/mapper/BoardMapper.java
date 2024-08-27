package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.cd.entity.Board;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface BoardMapper extends BaseMapper<Board> {
    /**
     * 根据板块ID获取帖子的数量
     * @param plateId 板块ID
     * @return 帖子数量
     */
    @Select("SELECT COUNT(*) FROM posts WHERE plateId = #{plateId}")
    Integer countPostsByPlateId(Integer plateId);

    @Select("Select * from plate where plateName = #{boardname}")
    Board getboardbyName(String boardname);
}
