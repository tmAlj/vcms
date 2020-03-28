package com.wsd.utils;

import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.*;

/**
 * @author tm
 * @version 1.0.0
 * @description 获取本机指标工具类
 * @updateRemark
 * @updateUser
 * @createDate 2020-3-28 15:48
 * @updateDate 2020-3-28 15:48
 */
@Slf4j
public class SigarUtils {

    public static void main(String[] args) {
        //getMemoryInfo()
    }

    /**
     * 查询系统内存信息
     * @throws SigarException
     */
    private static void getMemoryInfo() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        log.info("内存总量:    " + mem.getTotal() / 1024L + "K av");
        log.info("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
        log.info("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");

        Swap swap = sigar.getSwap();
        log.info("交换区总量:    " + swap.getTotal() / 1024L + "K av");
        log.info("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
        log.info("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
    }

    /**
     * 查询系统cpu信息
     * @throws SigarException
     */
    private static void getCpuInfo() throws SigarException {
        Sigar sigar = new Sigar();
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {
            CpuInfo info = infos[i];
            log.info("第" + (i + 1) + "块CPU信息");
            log.info("CPU的总量MHz:    " + info.getMhz());
            log.info("CPU生产商:    " + info.getVendor());
            log.info("CPU类别:    " + info.getModel());
            log.info("CPU缓存数量:    " + info.getCacheSize());
            printCpuPerc(cpuList[i]);
        }
    }

    /**
     * cpu使用率
     * @param cpu
     */
    private static void printCpuPerc(CpuPerc cpu) {
        log.info("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));
        log.info("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));
        log.info("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));
        log.info("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));
        log.info("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));
        log.info("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));
    }

    /**
     * 查询操作系统信息
     */
    private static void getOsInfo() {
        OperatingSystem OS = OperatingSystem.getInstance();
        log.info("操作系统:    " + OS.getArch());
        log.info("操作系统CpuEndian():    " + OS.getCpuEndian());
        log.info("操作系统DataModel():    " + OS.getDataModel());
        log.info("操作系统的描述:    " + OS.getDescription());
        log.info("操作系统的卖主:    " + OS.getVendor());
        log.info("操作系统的卖主名:    " + OS.getVendorCodeName());
        log.info("操作系统名称:    " + OS.getVendorName());
        log.info("操作系统卖主类型:    " + OS.getVendorVersion());
        log.info("操作系统的版本号:    " + OS.getVersion());
    }

    /**
     * 查询服务器信息
     * @throws SigarException
     */
    private static void getWhoInfo() throws SigarException {
        Sigar sigar = new Sigar();
        Who who[] = sigar.getWhoList();
        if (who != null && who.length > 0) {
            for (int i = 0; i < who.length; i++) {
                Who _who = who[i];
                log.info("用户控制台:    " + _who.getDevice());
                log.info("用户host:    " + _who.getHost());
                log.info("当前系统进程表中的用户名:    " + _who.getUser());
            }
        }
    }

    /**
     * 查询文件信息
     * @throws Exception
     */
    private static void getFileInfo() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            log.info("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            log.info("盘符名称:    " + fs.getDevName());
            log.info("盘符路径:    " + fs.getDirName());
            log.info("盘符标志:    " + fs.getFlags());
            log.info("盘符类型:    " + fs.getSysTypeName());
            log.info("盘符类型名:    " + fs.getTypeName());
            log.info("盘符文件系统类型:    " + fs.getType());
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                // TYPE_UNKNOWN ：未知
                case 0:
                    break;
                // TYPE_NONE
                case 1:
                    break;
                // TYPE_LOCAL_DISK : 本地硬盘
                case 2:
                    // 文件系统总大小
                    log.info(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    log.info(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
                    // 文件系统可用大小
                    log.info(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    log.info(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    log.info(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
                    break;
                // TYPE_NETWORK ：网络
                case 3:
                    break;
                // TYPE_RAM_DISK ：闪存
                case 4:
                    break;
                // TYPE_CDROM ：光驱
                case 5:
                    break;
                // TYPE_SWAP ：页面交换
                case 6:
                    break;
            }
            log.info(fs.getDevName() + "读出：    " + usage.getDiskReads());
            log.info(fs.getDevName() + "写入：    " + usage.getDiskWrites());
        }
        return;
    }

    /**
     * 查询网络信息
     * @throws Exception
     */
    private static void getNetInfo() throws Exception {
        Sigar sigar = new Sigar();
        String ifNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < ifNames.length; i++) {
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            log.info("网络设备名:    " + name);
            log.info("IP地址:    " + ifconfig.getAddress());
            log.info("子网掩码:    " + ifconfig.getNetmask());
            if ((ifconfig.getFlags() & 1L) <= 0L) {
                log.info("!IFF_UP...skipping getNetInterfaceStat");
                continue;
            }
            NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
            log.info(name + "接收的总包裹数:" + ifstat.getRxPackets());
            log.info(name + "发送的总包裹数:" + ifstat.getTxPackets());
            log.info(name + "接收到的总字节数:" + ifstat.getRxBytes());
            log.info(name + "发送的总字节数:" + ifstat.getTxBytes());
            log.info(name + "接收到的错误包数:" + ifstat.getRxErrors());
            log.info(name + "发送数据包时的错误数:" + ifstat.getTxErrors());
            log.info(name + "接收时丢弃的包数:" + ifstat.getRxDropped());
            log.info(name + "发送时丢弃的包数:" + ifstat.getTxDropped());
        }
    }

    /**
     * 查询以太网信息
     * @throws SigarException
     */
    private static void getEthernetInfo() throws SigarException {
        Sigar sigar = null;
        sigar = new Sigar();
        String[] ifaces = sigar.getNetInterfaceList();
        for (int i = 0; i < ifaces.length; i++) {
            NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
            if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
                    || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
                    || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
                continue;
            }
            log.info(cfg.getName() + "IP地址:" + cfg.getAddress());
            log.info(cfg.getName() + "网关广播地址:" + cfg.getBroadcast());
            log.info(cfg.getName() + "网卡MAC地址:" + cfg.getHwaddr());
            log.info(cfg.getName() + "子网掩码:" + cfg.getNetmask());
            log.info(cfg.getName() + "网卡描述信息:" + cfg.getDescription());
            log.info(cfg.getName() + "网卡类型" + cfg.getType());
        }
    }
}