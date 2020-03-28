package com.wsd.service.impl;

import com.wsd.entity.Role;
import com.wsd.entity.User;
import com.wsd.mapper.RoleMapper;
import com.wsd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户service
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

    //注入用户mapper
    @Autowired
    private UserMapper userMapper;
    //注入系统角色mapper
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 重写loadUserByUsername方法
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("账户名或者密码输入错误!");
        }
        List<Role> roleList = roleMapper.queryRoleListByUserId(user.getUserId());
        user.setRoleList(roleList);
        return user;
    }
}
