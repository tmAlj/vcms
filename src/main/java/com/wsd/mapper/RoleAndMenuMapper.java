package com.wsd.mapper;

import com.wsd.entity.RoleAndMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统角色菜单mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface RoleAndMenuMapper extends BaseMapper<RoleAndMenu>{

    List<Long> getMenuIdsByRoleId(Long roleId);

    void updateRoleAndMenu(Long roleId, Long[] menuIdList);
}
