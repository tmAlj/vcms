package com.wsd.mapper;

import com.wsd.entity.Orgn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 系统组织mapper
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-3 16:05
 * @updateDate 2020-3-3 16:05
 */
@Repository
public interface OrgnMapper extends BaseMapper<Orgn>{

    List<Orgn> getOrgnList();

    void deleteOrgn(@Param("orgnIds") Long[] orgnIds);
}
