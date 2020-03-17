package com.wsd.mapper;

import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户mapper
 */
@Repository
public interface UserMapper extends BaseMapper<User>{

    User loadUserByUsername(@Param("username")String username);

    List<User> getUserList(@Param("user")User user);

    void deleteUser(@Param("userIds")Long[] userIds);

    List<User> getUserListByPostId(@Param("postIds")Long[] postIds);

    List<User> getUserListByOrgnId(@Param("orgnIds")Long[] orgnIds);
}
