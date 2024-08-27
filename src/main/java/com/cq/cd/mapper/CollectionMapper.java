package com.cq.cd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cq.cd.entity.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

    /**
     * 通过邮箱返回收藏帖子
     * @param email
     * @return
     */
    @Select("select * from collection where email = #{email}")
    List<Collection> getCollectionTable(String email);

}
