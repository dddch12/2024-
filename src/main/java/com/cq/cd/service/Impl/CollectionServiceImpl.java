package com.cq.cd.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cq.cd.entity.Collection;
import com.cq.cd.mapper.CollectionMapper;
import com.cq.cd.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<Collection> getCollectionTable(String email){
        return collectionMapper.getCollectionTable(email);
    }
}
