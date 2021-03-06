package com.wsd.service.impl;

import com.wsd.entity.User;
import com.wsd.mapper.UserMapper;
import com.wsd.service.UserAndRoleService;
import com.wsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户serviceImpl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAndRoleService userAndRoleService;

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    /**
     * 通过账户查询用户信息
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Transactional
    @Override
    public void addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String passward = encoder.encode("123456");
        //设置密码
        user.setPassword(passward);
        user.setCreateTime(new Date());
        userMapper.save(user);
        //保存角色
        userAndRoleService.addOrUpdateRole(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 删除用户
     * @param userIds 用户id数组
     */
    @Override
    public void deleteUser(Long[] userIds){
        userMapper.deleteUser(userIds);
    }

    /**
     * 修改用户
     * @param user
     */
    @Transactional
    @Override
    public void updateUser(User user){
        userMapper.update(user);
        //保存角色
        userAndRoleService.addOrUpdateRole(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 重置用户密码
     * @param user
     */
    @Override
    public void resetUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String passward = encoder.encode("123456");
        //设置密码
        user.setPassword(passward);
        userMapper.update(user);
    }

    /**
     * 根据职位查询用户列表
     * @param postIds
     * @return
     */
    @Override
    public List<User> getUserListByPostId(Long[] postIds) {
        return userMapper.getUserListByPostId(postIds);
    }

    /**
     * 根据部门查询用户列表
     * @param orgnIds
     * @return
     */
    @Override
    public List<User> getUserListByOrgnId(Long[] orgnIds) {
        return userMapper.getUserListByOrgnId(orgnIds);
    }
}
