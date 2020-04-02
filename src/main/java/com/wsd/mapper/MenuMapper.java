package com.wsd.mapper;

import com.wsd.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统菜单mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

    List<Menu> getMenuTree();

    List<Menu> getMenuList();

    void deleteMenu(@Param("menuIdList") Long[] menuIdList);
}
