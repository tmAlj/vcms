package com.wsd.service.impl;

import com.wsd.entity.Role;
import com.wsd.mapper.RoleAndMenuMapper;
import com.wsd.service.RoleAndMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色菜单impl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Service
@Transactional
public class RoleAndMenuServiceImpl implements RoleAndMenuService {

    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;

    /**
     * 根据角色id查询对应菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return roleAndMenuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public void updateRoleAndMenu(Role role) {

        List<Long> menuIdList = role.getMenuIdList();
        Long roleId = role.getRoleId();

        if(menuIdList.size() == 0){
            return ;
        }
        //先删除角色与菜单关系
        roleAndMenuMapper.delete(roleId);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        roleAndMenuMapper.save(map);
    }
}
