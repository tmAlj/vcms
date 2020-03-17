package com.wsd.service;

import com.wsd.entity.Post;
import com.wsd.entity.User;

import java.util.List;

/**
 * 系统职位service
 */
public interface PostService {

    List<Post> getPostList(Post post);

    void addPost(Post post);

    void deletePost(Long[] postIds);

    void updatePost(Post post);
}
