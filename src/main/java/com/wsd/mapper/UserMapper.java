package com.wsd.mapper;

import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
@Repository
public interface UserMapper extends BaseMapper<User>{

    User loadUserByUsername(@Param("username")String username);

    List<User> getUserList(@Param("user")User user);

    void deleteUser(@Param("userIds")Long[] userIds);

    List<User> getUserListByPostId(@Param("postIds")Long[] postIds);

    List<User> getUserListByOrgnId(@Param("orgnIds")Long[] orgnIds);
}
