package com.wsd.mapper;

import com.wsd.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统菜单mapper
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> getAllMenu();

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

    List<Menu> getMenuTree();

    List<Menu> getMenuList();

    void deleteMenu(@Param("menuIdList") Long[] menuIdList);
}
