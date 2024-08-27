package com.cq.cd.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.cd.entity.Board;
import com.cq.cd.entity.Post;
import com.cq.cd.entity.User;
import com.cq.cd.service.BoardService;
import com.cq.cd.service.PostService;
import com.cq.cd.service.ReviewService;
import com.cq.cd.service.UserService;
import com.cq.cd.util.ApiResult;
import com.cq.cd.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制器
 * 提供管理员功能
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private BoardService boardService;


    /**
     * 分页查询所有用户
     *
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult 分页结果
     */
    @GetMapping("/users/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<User> userPage = new Page<>(page, size);
        IPage<User> res = userService.page(userPage);
        Map<String, Object> data = new HashMap<>();
        data.put("users", res);
        return ApiResult.buildApiResult(200, "分页查询所有用户", data);
    }

    /**
     * 根据用户名称查询用户详情
     * @param username 用户名称
     * @return ApiResult 查询结果
     */
    @GetMapping("/users/{username}")
    public ApiResult findById(@PathVariable("username") String username) {
        User res = userService.getuserbyName(username);
        Map<String, Object> data = new HashMap<>();
        if (res != null) {
            data.put("user", res);
            return ApiResult.buildApiResult(200, "请求成功", data);
        } else {
            return ApiResult.buildApiResult(404, "查询的用户不存在", null);
        }
    }

    /**
     * 根据用户名称删除用户
     * @param username 用户名称
     * @return ApiResult 删除结果
     */
    @DeleteMapping("/users/{username}")
    public ApiResult deleteById(@PathVariable("username") String username) {
        User user = userService.getuserbyName(username);
        boolean res = userService.removeById(user.getUserId());
        Map<String, Object> data = new HashMap<>();
        data.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "删除成功", data);
        } else {
            return ApiResult.buildApiResult(400, "删除失败", data);
        }
    }

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return ApiResult 更新结果
     */
    @PutMapping("/users")
    public ApiResult update(@RequestBody User user) {
        boolean res = userService.updateById(user);
        Map<String, Object> data = new HashMap<>();
        data.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "更新成功", data);
        } else {
            return ApiResult.buildApiResult(400, "更新失败", data);
        }
    }

    /**
     * 添加新用户
     * @param user 用户对象
     * @return ApiResult 添加结果
     */
    @PostMapping("/users")
    public ApiResult add(@RequestBody User user) {
        user.setUserCreatedData(LocalDate.now());
        boolean res = userService.save(user);
        Map<String, Object> data = new HashMap<>();
        data.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "添加成功", data);
        } else {
            return ApiResult.buildApiResult(400, "添加失败", data);
        }
    }

    /**
     * 更新用户密码
     * @param user 用户对象，必须包含名称和密码
     * @return ApiResult 更新结果
     */
    @PutMapping("/password")
    public ApiResult updatePwd(@RequestBody User user) {
        // 更新密码
        int res = userService.updatePwd(user);
        if (res >= 0) {
            return ApiResult.buildApiResult(200, "密码更新成功", null);
        } else {
            return ApiResult.buildApiResult(400, "密码更新失败", null);
        }
    }


    /**
     * 更新帖子信息
     * @param post 帖子对象
     * @return ApiResult 更新结果
     */
    @PutMapping("/update")
    public ApiResult update(@RequestBody Post post) {
        boolean res = postService.updateById(post);
        Map<String, Object> data = new HashMap<>();
        data.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "更新成功", data);
        }
        return ApiResult.buildApiResult(400, "更新失败", data);
    }

    /**
     * 管理用户登录
     * @param user 用户对象，必须包含用户名和密码
     * @return ApiResult 登录结果
     */
    @PostMapping("/auth/login")
    public ApiResult authLogin(@RequestBody User user) {
        if (userService.authlogin(user)) {
            String token = JwtTokenUtil.generateToken(user.getUserName());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("token", token);
            return ApiResult.buildApiResult(200, "认证登录成功", resultMap);
        } else {
            return ApiResult.buildApiResult(401, "认证登录失败", null);
        }
    }

    /**
     * 获取用户总数
     *
     * @return ApirResult 返回结果
     */
    @GetMapping("/usercount")
    public Long getUserCount() {
        return userService.count();
    }

    /**
     * 获取评论总数
     *
     * @return ApirResult 返回结果
     */
    @GetMapping("/postcount")
    public Long getReviewCount() {
        return reviewService.count();
    }


    /**
     * 根据用户ID删除用户
     *
     * @param userId 用户ID
     * @return ApiResult 删除结果
     */
    @DeleteMapping("/{userId}")
    public ApiResult deleteById(@PathVariable("userId") Integer userId) {
        boolean res = userService.removeById(userId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", res);
        return ApiResult.buildApiResult(200, "删除成功", resultMap);
    }

    /**
     * 返回所有帖子
     *
     * @return 帖子集合
     */
    @GetMapping("/getall")
    public ApiResult getallpost() {
        List<Post> list = postService.list();
        return ApiResult.success().data("postList", list);
    }


    /**
     * 分页查询所有帖子
     *
     * @param page 当前页码
     * @param size 每页大小
     * @return ApiResult 分页结果
     */
    @GetMapping("/post/{page}/{size}")
    public ApiResult findpost(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Post> postPage = new Page<>(page, size);
        IPage<Post> res = postService.page(postPage);
        Map<String, Object> data = new HashMap<>();
        data.put("users", res);
        return ApiResult.buildApiResult(200, "分页查询所有帖子", data);
    }


    /**
     * 更新板块信息
     *
     * @param board 帖子对象
     * @return ApiResult 更新结果
     */
    @PutMapping("/updateboard")
    public ApiResult updateboard(@RequestBody Board board) {
        boolean res = boardService.updateById(board);
        Map<String, Object> data = new HashMap<>();
        data.put("success", res);
        if (res) {
            return ApiResult.buildApiResult(200, "更新成功", data);
        }
        return ApiResult.buildApiResult(400, "更新失败", data);
    }
}