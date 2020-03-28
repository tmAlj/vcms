package com.wsd.controller.api;

import com.wsd.utils.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tm
 * @version 1.0.0
 * @description 服务器监控api
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-28 18:59
 * @updateDate 2020-3-28 18:59
 */
@Api(description = "服务器监控接口")
@RestController
public class MonitorApi {

    /**
     * 查询内存信息
     *
     * @return
     */
    @ApiOperation(value = "获取内存信息", notes="获取内存信息")
    @GetMapping("/getMemoryInfo")
    public ResultData getMemoryInfo() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        return ResultData.ok().put("memoryInfo", mem);
    }

    /**
     * 查询操作系统信息
     *
     * @return
     */
    @ApiOperation(value = "查询操作系统信息", notes="查询操作系统信息")
    @GetMapping("/getOsInfo")
    public ResultData getOsInfo() throws SigarException {
        OperatingSystem operatingSystem = OperatingSystem.getInstance();
        return ResultData.ok().put("osInfo", operatingSystem);
    }
}
