package com.cq.cd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.cd.entity.Review;
import com.cq.cd.service.ReviewService;
import com.cq.cd.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/review")  // 标准化路由映射
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /**
     * 获取分页的所有评论
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult 分页结果
     */
    @GetMapping("/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Review> reviewPage = new Page<>(page, size);
        IPage<Review> res = reviewService.page(reviewPage);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", res);
        return ApiResult.buildApiResult(200, "分页查询所有评论", resultMap);
    }

    /**
     * 根据ID获取评论详情
     * @param reviewId 评论ID
     * @return ApiResult 查询结果
     */
    @GetMapping("/{reviewId}")
    public ApiResult findById(@PathVariable("reviewId") Integer reviewId) {
        Review res = reviewService.getById(reviewId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", res);
        if (res != null) {
            return ApiResult.buildApiResult(200, "请求成功", resultMap);
        } else {
            return ApiResult.buildApiResult(404, "查询的评论不存在", null);
        }
    }

    /**
     * 根据ID删除评论
     * @param reviewId 评论ID
     * @return ApiResult 删除结果
     */
    @DeleteMapping("/{reviewId}")
    public ApiResult deleteById(@PathVariable("reviewId") Integer reviewId) {
        boolean res = reviewService.removeById(reviewId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", res);
        return ApiResult.buildApiResult(200, "删除成功", resultMap);
    }

    /**
     * 更新评论
     * @param review 评论对象
     * @return ApiResult 更新结果
     */
    @PutMapping("/")
    public ApiResult update(@RequestBody Review review) {
        boolean res = reviewService.updateById(review);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "更新成功", resultMap);
        }
        return ApiResult.buildApiResult(400, "更新失败", resultMap);
    }

    /**
     * 新增评论
     * @param review 评论对象
     * @return ApiResult 添加结果
     */
    @PostMapping("/")
    public ApiResult add(@RequestBody Review review) {
        boolean res = reviewService.save(review);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "添加成功", resultMap);
        } else {
            return ApiResult.buildApiResult(400, "添加失败", resultMap);
        }
    }

    /**
     * 批量删除评论
     * @param reviewIds 评论ID列表
     * @return ApiResult 批量删除结果
     */
    @DeleteMapping("/batch")
    public ApiResult deleteBatch(@RequestBody List<Integer> reviewIds) {
        boolean res = reviewService.removeByIds(reviewIds);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", res);
        return ApiResult.buildApiResult(200, "批量删除成功", resultMap);
    }

    /**
     * 根据条件查询评论
     * @param review 评论查询条件
     * @return ApiResult 查询结果
     */
    @PostMapping("/search")
    public ApiResult search(@RequestBody Review review) {
        List<Review> res = reviewService.list(new QueryWrapper<>(review));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", res);
        return ApiResult.buildApiResult(200, "条件查询成功", resultMap);
    }
}
