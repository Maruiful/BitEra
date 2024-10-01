package com.github.paicoding.forum.core.mdc;

import com.github.paicoding.forum.core.util.IpUtil;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 自定义的traceId生成器
 * <p>
 * 生成规则参考 <a href="https://help.aliyun.com/document_detail/151840.html"/>
 * */
@Slf4j
public class SelfTraceIdGenerator {
    private final static Integer MIN_AUTO_NUMBER = 1000;
    private final static Integer MAX_AUTO_NUMBER = 10000;
    private static volatile Integer autoIncreaseNumber = MIN_AUTO_NUMBER;

    /**
     * <p>
     * 生成32位traceId，规则是 服务器 IP + 产生ID时的时间 + 自增序列 + 当前进程号
     * IP 8位：39.105.208.175 -> 2769d0af
     * 产生ID时的时间 13位： 毫秒时间戳 -> 1403169275002
     * 当前进程号 5位： PID
     * 自增序列 4位： 1000-9999循环
     * </p>
     * w
     *
     * @return ac13e001.1685348263825.095001000
     */
    public static String generate()  { return null; }

    /**
     * IP转换为十六进制 - 8位
     *
     * @param ip 39.105.208.175
     * @return 2769d0af
     */
    private static String convertIp(String ip)  { return null; }

    /**
     * 使得自增序列在1000-9999之间循环  - 4位
     *
     * @return 自增序列号
     */
    private static int getAutoIncreaseNumber()  { return 0; }

    /**
     * @return 5位当前进程号
     */
    private static String getProcessId()  { return null; }

    public static void main(String[] args)  {}
}
