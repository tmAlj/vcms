package com.wsd.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsd.entity.Post;
import com.wsd.entity.User;
import com.wsd.service.PostService;
import com.wsd.service.UserService;
import com.wsd.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 职位controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-15 13:34
 * @updateDate 2020-3-15 13:34
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    /**
     * 职位列表页面
     * @return
     */
    @GetMapping("/post/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("post/list");
        return mv;
    }

    /**
     * 职位新增页面
     * @return
     */
    @GetMapping("/post/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("post/add");
        return mv;
    }

    /**
     * 职位修改页面
     * @return
     */
    @GetMapping("/post/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("post/update");
        return mv;
    }

    /**
     * 查询职位列表
     * @return
     */
    @GetMapping("/post/getPostList")
    public ResultData getPostList(Integer page, Integer limit, String name){
        Post post = new Post();
        post.setName(name);
        PageHelper.startPage(page, limit);
        List<Post> postList = postService.getPostList(post);
        PageInfo<Post> p = new PageInfo<Post>(postList);
        return ResultData.ok().put("page", p);
    }

    /**
     * 新增职位
     * @return
     */
    @PostMapping("/post/addPost")
    public ResultData addPost(@RequestBody Post post){
        postService.addPost(post);
        return ResultData.ok();
    }

    /**
     * 删除职位
     * @return
     */
    @PostMapping("/post/deletePost")
    public ResultData deletePost(@RequestBody Long[] postIds){
        //验证该职位是否被使用
        List<User> userList = userService.getUserListByPostId(postIds);
        if(userList.size() > 0){
            return ResultData.error("职位已被使用，不能删除");
        }
        postService.deletePost(postIds);
        return ResultData.ok();
    }

    /**
     * 修改职位
     * @return
     */
    @PostMapping("/post/updatePost")
    public ResultData updatePost(@RequestBody Post post){
        postService.updatePost(post);
        return ResultData.ok();
    }
}
