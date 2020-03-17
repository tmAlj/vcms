package com.wsd.mapper;

import com.wsd.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统角色mapper
 */
@Repository
public interface RoleMapper extends BaseMapper<Role>{

    List<Role> queryRoleListByUserId(@Param("userId") Long userId);

    List<Role> getRoleList(@Param("role") Role role);

    Role getRole(@Param("role") Role role);

    void deleteRole(@Param("roleIds") Long[] roleIds);
}
