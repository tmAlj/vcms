package com.wsd.service.impl;

import com.wsd.entity.Orgn;
import com.wsd.mapper.OrgnMapper;
import com.wsd.service.OrgnService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统组织serviceImpl
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
@Service
public class OrgnServiceImpl implements OrgnService {

    @Autowired
    private OrgnMapper orgnMapper;

    /**
     * 查询组织列表
     * @return
     */
    @Override
    public List<Orgn> getOrgnList() {
        //查询用户系统菜单
        List<Orgn> rootOrgn = orgnMapper.getOrgnList();
        List<Orgn> orgnList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootOrgn.size(); i++) {
            if (rootOrgn.get(i).getParentId() == 0L) {
                orgnList.add(rootOrgn.get(i));
            }
        }
        // 为一级组织设置子组织
        for (Orgn orgn : orgnList) {
            orgn.setChildren(getChild(orgn.getOrgnId(), rootOrgn)); ;
        }
        return orgnList;
    }

    /**
     * 新增组织
     * @param orgn
     */
    @Override
    public void addOrgn(Orgn orgn) {
        orgn.setCreateTime(new Date());
        orgnMapper.save(orgn);
    }

    /**
     * 删除组织
     * @param orgnIds
     */
    @Override
    public void deleteOrgn(Long[] orgnIds) {
        orgnMapper.deleteOrgn(orgnIds);
    }

    /**
     * 修改组织
     * @param orgn
     */
    @Override
    public void updateOrgn(Orgn orgn) {
        orgnMapper.update(orgn);
    }

    /**
     * 递归遍历子组织
     * @param id
     * @param rootOrgn
     * @return
     */
    List<Orgn> getChild(Long id, List<Orgn> rootOrgn) {
        // 子组织
        List<Orgn> childList = new ArrayList<Orgn>();
        for (Orgn orgn : rootOrgn) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(orgn.getParentId().toString())) {
                if (orgn.getParentId().equals(id)) {
                    childList.add(orgn);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Orgn orgn : childList) {
            //if (menu.getUrl() != "") {
            // 递归
            orgn.setChildren(getChild(orgn.getOrgnId(), rootOrgn));
            // }
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
