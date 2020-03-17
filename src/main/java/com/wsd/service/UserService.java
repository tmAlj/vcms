package com.wsd.service;

import com.wsd.entity.Role;
import com.wsd.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户service
 */
public interface UserService {

    List<User> getUserList(User user);

    User getUserByUsername(String username);

    void addUser(User user);

    void deleteUser(Long[] userIds);

    void updateUser(User user);

    void resetUser(User user);

    List<User> getUserListByPostId(Long[] postIds);

    List<User> getUserListByOrgnId(Long[] orgnIds);

}
