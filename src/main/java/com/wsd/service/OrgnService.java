package com.wsd.service;

import com.wsd.entity.Orgn;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统组织service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-22 14:49
 * @updateDate 2020-3-22 14:49
 */
public interface OrgnService {

    List<Orgn> getOrgnList();

    void addOrgn(Orgn orgn);

    void deleteOrgn(Long[] orgnIds);

    void updateOrgn(Orgn org);
}
