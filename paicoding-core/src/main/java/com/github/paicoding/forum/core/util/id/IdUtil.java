package com.github.paicoding.forum.core.util.id;

import com.github.paicoding.forum.api.model.enums.pay.ThirdPayWayEnum;
import com.github.paicoding.forum.core.async.AsyncUtil;
import com.github.paicoding.forum.core.util.CompressUtil;
import com.github.paicoding.forum.core.util.DateUtil;
import com.github.paicoding.forum.core.util.id.snowflake.PaiSnowflakeIdGenerator;
import com.github.paicoding.forum.core.util.id.snowflake.SnowflakeProducer;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicLong;

import static com.github.paicoding.forum.core.util.CompressUtil.int2str;

/** */
public class IdUtil {
    /**
     * 默认的id生成器
     */
    public static SnowflakeProducer DEFAULT_ID_PRODUCER = new SnowflakeProducer(new PaiSnowflakeIdGenerator());

    private static AtomicLong INCR = new AtomicLong((int) (Math.random() * 500));
    private static long lastTime = 0;


    /**
     * 生成全局id
     *
     * @return
     */
    public static Long genId()  { return null; }

    /**
     * 生成字符串格式全局id
     *
     * @return
     */
    public static String genStrId()  { return null; }


    /**
     * 生成支付的唯一code
     * 简化的规则：payWay前缀 + 年月日+时分秒
     *
     * @return
     */
    public static String genPayCode(ThirdPayWayEnum payWay, Long id)  { return null; }

    /**
     * 根据payCode 解析获取 payId
     *
     * @param code
     * @return
     */
    public static Long getPayIdFromPayCode(String code)  { return null; }

    public static void main(String[] args)  {}
}
