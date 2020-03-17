package com.wsd.service;

import com.wsd.entity.Orgn;

import java.util.List;

/**
 * 系统组织service
 */
public interface OrgnService {

    List<Orgn> getOrgnList();

    void addOrgn(Orgn orgn);

    void deleteOrgn(Long[] orgnIds);

    void updateOrgn(Orgn org);
}
