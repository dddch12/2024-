package com.cq.cd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.cd.entity.Board;
import com.cq.cd.entity.Post;
import com.cq.cd.service.BoardService;
import com.cq.cd.service.PostService;
import com.cq.cd.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版块控制器
 * 提供与板块相关的接口
 */
@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;


    /**
     * 分页查询指定板块下的所有帖子
     *
     * @param boardName 板块名称
     * @param page      当前页码
     * @param size      每页大小
     * @return ApiResult 分页结果 根据时间排序后返回
     */
    @GetMapping("/{boardName}/{page}/{size}")
    public ApiResult findAllByBoardName(@PathVariable String boardName, @PathVariable Integer page, @PathVariable Integer size) {


        Board board = boardService.getboardbyName(boardName);
        if (board == null) {
            return ApiResult.buildApiResult(404, "指定的板块不存在", null);
        }

        // 获取板块 ID
        Integer boardId = board.getPlateId();

        // 查询指定板块下的帖子
        Page<Post> postPage = new Page<>(page, size);
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plateId", boardId);  // 确保在数据库中有这个字段
        IPage<Post> res = postService.page(postPage, queryWrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("posts", res);
        return ApiResult.buildApiResult(200, "分页查询指定板块下的所有帖子", data);
    }

    /**
     * 获取今日版块的帖子总数
     * @param boardId 版块ID
     * @return ApiResult 今日帖子总数
     */
    @GetMapping("/todayThreadNum/{boardId}")
    public ApiResult getTodayThreadNum(@PathVariable("boardId") Integer boardId) {
        Integer postnumToday = boardService.countPostsCreatedTodayByPlateId(boardId);
        if (postnumToday != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("postnumToday", postnumToday);
            return ApiResult.buildApiResult(200, "今日帖子总数获取成功", data);
        } else {
            return ApiResult.buildApiResult(404, "版块不存在", null);
        }
    }

    /**
     * 获取所有板块的信息
     * @return ApiResult 所有板块的信息
     */
    @GetMapping("/all")
    public ApiResult getAllBoards() {
        List<Board> boards = boardService.list();
        Map<String, Object> data = new HashMap<>();
        data.put("boards", boards);
        for (Board board : boards) {
            Integer postcount = boardService.countPostsByPlateId(board.getPlateId());
            data.put("postcount", postcount);
        }
        if (boards != null && !boards.isEmpty()) {
            return ApiResult.buildApiResult(200, "获取所有板块信息成功", data);
        } else {
            return ApiResult.buildApiResult(404, "没有板块信息", data);
        }
    }
}
