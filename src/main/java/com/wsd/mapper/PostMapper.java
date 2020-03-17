package com.wsd.mapper;

import com.wsd.entity.Post;
import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统职位mapper
 */
@Repository
public interface PostMapper extends BaseMapper<Post>{

    List<Post> getPostList(@Param("post") Post post);

    void deletePost(@Param("postIds")Long[] postIds);
}
