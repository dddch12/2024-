package com.cq.cd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cq.cd.entity.Collection;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CollectionService extends IService<Collection> {

    /**
     * 根据邮箱获取收藏帖子
     * @param email
     * @return 收藏帖子
     */
    List<Collection> getCollectionTable(String email);
}
