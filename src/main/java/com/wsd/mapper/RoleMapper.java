package com.wsd.mapper;

import com.wsd.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface RoleMapper extends BaseMapper<Role>{

    List<Role> queryRoleListByUserId(@Param("userId") Long userId);

    List<Role> getRoleList(@Param("role") Role role);

    Role getRole(@Param("role") Role role);

    void deleteRole(@Param("roleIds") Long[] roleIds);
}
