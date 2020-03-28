package com.wsd.service;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统用户角色service
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
public interface UserAndRoleService {

    void addOrUpdateRole(Long userId, List<Long> roleIdList);

}
