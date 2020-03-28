package com.wsd.service.impl;

import com.wsd.mapper.UserAndRoleMapper;
import com.wsd.service.UserAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户角色impl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Service
public class UserAndRoleServiceImpl implements UserAndRoleService {

    @Autowired
    UserAndRoleMapper userAndRoleMapper;

    /**
     * 更新用户角色
     * @param userId
     * @param roleIdList
     */
    @Transactional
    @Override
    public void addOrUpdateRole(Long userId, List<Long> roleIdList) {
        if(roleIdList.size() == 0){
            return ;
        }
        //先删除用户与角色关系
        userAndRoleMapper.delete(userId);
        //保存用户与角色关系,可选多个角色
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("roleIdList", roleIdList);
        userAndRoleMapper.save(map);
    }
}
