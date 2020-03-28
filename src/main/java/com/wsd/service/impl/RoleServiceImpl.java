package com.wsd.service.impl;

import com.wsd.entity.Role;
import com.wsd.mapper.RoleMapper;
import com.wsd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 系统角色service
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户id查询所属的角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> queryRoleListByUserId(Long userId) {
        return roleMapper.queryRoleListByUserId(userId);
    }

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    @Override
    public List<Role> getRoleList(Role role) {
        return roleMapper.getRoleList(role);
    }

    /**
     * 新增角色
     * @param role
     */
    @Override
    public void addRole(Role role) {
        role.setCreateTime(new Date());
        roleMapper.save(role);
    }

    /**
     * 查询角色信息
     * @param role
     * @return
     */
    @Override
    public Role getRole(Role role) {
        return roleMapper.getRole(role);
    }

    /**
     * 删除角色
     * @param roleIds
     */
    @Override
    public void deleteRole(Long[] roleIds) {
        roleMapper.deleteRole(roleIds);
    }

    /**
     * 修改角色
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        roleMapper.update(role);
    }

}
