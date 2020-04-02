package com.wsd.service;

import com.wsd.entity.Role;
import com.wsd.entity.User;
import com.wsd.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface RoleService {

    List<Role> queryRoleListByUserId(Long userId);

    List<Role> getRoleList(Role role);

    void addRole(Role role);

    Role getRole(Role role);

    void deleteRole(Long[] roleIds);

    void updateRole(Role role);
}
