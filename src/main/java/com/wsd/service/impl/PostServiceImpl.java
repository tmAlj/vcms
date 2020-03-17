package com.wsd.service.impl;

import com.wsd.entity.Post;
import com.wsd.entity.User;
import com.wsd.mapper.PostMapper;
import com.wsd.mapper.UserMapper;
import com.wsd.service.PostService;
import com.wsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统职位serviceImpl
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    /**
     * 查询职位列表
     * @param post
     * @return
     */
    @Override
    public List<Post> getPostList(Post post) {
        return postMapper.getPostList(post);
    }

    /**
     * 新增职位
     * @param post
     */
    @Override
    public void addPost(Post post) {
        post.setCreateTime(new Date());
        postMapper.save(post);
    }

    /**
     * 删除职位
     * @param postIds
     */
    @Override
    public void deletePost(Long[] postIds) {
        postMapper.deletePost(postIds);
    }

    /**
     * 修改职位
     * @param post
     */
    @Override
    public void updatePost(Post post) {
        postMapper.update(post);
    }
}
