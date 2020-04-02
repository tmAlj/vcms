package com.wsd.service;

import com.wsd.entity.Post;
import com.wsd.entity.User;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统职位service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface PostService {

    List<Post> getPostList(Post post);

    void addPost(Post post);

    void deletePost(Long[] postIds);

    void updatePost(Post post);
}
