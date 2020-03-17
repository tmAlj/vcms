package com.wsd.mapper;

import com.wsd.entity.Orgn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统组织mapper
 */
@Repository
public interface OrgnMapper extends BaseMapper<Orgn>{

    List<Orgn> getOrgnList();

    void deleteOrgn(@Param("orgnIds") Long[] orgnIds);
}
