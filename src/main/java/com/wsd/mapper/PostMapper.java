package com.wsd.mapper;

import com.wsd.entity.Post;
import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统职位mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface PostMapper extends BaseMapper<Post>{

    List<Post> getPostList(@Param("post") Post post);

    void deletePost(@Param("postIds")Long[] postIds);
}
