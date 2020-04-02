package com.wsd.service;

import com.wsd.entity.User;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
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
