package com.cq.cd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.cd.entity.Collection;
import com.cq.cd.entity.Post;
import com.cq.cd.entity.User;
import com.cq.cd.service.CollectionService;
import com.cq.cd.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{email}")
    public  ApiResult getCollectionTable(@RequestBody String email){
        List<Collection> collections = collectionService.getCollectionTable(email);
        Map<String,Object> data = new HashMap<>();
        data.put("collection",data);
        return ApiResult.buildApiResult(200, "收藏帖子查询结果", data);
    }

}
