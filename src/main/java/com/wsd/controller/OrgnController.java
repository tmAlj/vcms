package com.wsd.controller;

import com.wsd.entity.Orgn;
import com.wsd.entity.User;
import com.wsd.service.OrgnService;
import com.wsd.service.UserService;
import com.wsd.utils.ResultData;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author tm
 * @version 1.0.0
 * @description 组织controller
 * @updateRemark
 * @updateUser
 * @createDate 2020-2-26 14:14
 * @updateDate 2020-2-26 14:14
 */
@RestController
public class OrgnController {

    @Autowired
    OrgnService orgnService;
    @Autowired
    private UserService userService;

    /**
     * 组织列表页面
     * @return
     */
    @GetMapping("/orgn/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orgn/list");
        return mv;
    }

    /**
     * 组织新增页面
     * @return
     */
    @GetMapping("/orgn/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orgn/add");
        return mv;
    }

    /**
     * 组织修改页面
     * @return
     */
    @GetMapping("/orgn/update")
    public ModelAndView update() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orgn/update");
        return mv;
    }

    /**
     * 查询组织列表
     */
    @GetMapping("/orgn/getOrgnList")
    public ResultData getOrgnList(){
        List<Orgn> orgnList = orgnService.getOrgnList();
        return ResultData.ok().put("orgnList", orgnList);
    }

/*    *//**
     * 查询组织列表
     *//*
    @GetMapping("/orgn/getOrgnList1")
    public List<Orgn> getOrgnList1(){
        List<Orgn> orgnList = orgnService.getOrgnList();
        return orgnList;
    }*/


    /**
     * 新增组织
     * @return
     */
   @PostMapping("/orgn/addOrgn")
    public ResultData addOrgn(@RequestBody Orgn orgn){
        orgnService.addOrgn(orgn);
        return ResultData.ok();
    }

    /**
     * 删除组织
     * @return
     */
    @PostMapping("/orgn/deleteOrgn")
    public ResultData deleteOrgn(@RequestBody Long[] orgnIds){
        if(ArrayUtils.contains(orgnIds, 1L)){
            return ResultData.error("系统组织不能删除");
        }
        //判断部门是否被使用
        List<User> orgnList = userService.getUserListByOrgnId(orgnIds);
        if(orgnList.size() > 0){
            return ResultData.error("组织已被使用，不能删除");
        }
        orgnService.deleteOrgn(orgnIds);
        return ResultData.ok();
    }

    /**
     * 修改组织
     * @param orgn
     * @return
     */
    @PostMapping("/orgn/updateOrgn")
    public ResultData updateOrgn(@RequestBody Orgn orgn){
        orgnService.updateOrgn(orgn);
        return ResultData.ok();
    }
}
