package com.github.paicoding.forum.service.notify.help;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import com.github.paicoding.forum.api.model.vo.notify.NotifyMsgEvent;
import org.springframework.stereotype.Service;

/** */
@Service
public class MsgNotifyHelper {

    /**
     * 消息广播通知
     *
     * @param type    消息类型
     * @param content 消息内容
     * @param <T>     消息类型
     */
    public <T> void publishMsg(NotifyTypeEnum type, T content) {}


    /**
     * 静态方法使用方式，简化调用方使用
     *
     * @param type 消息类型
     *             * @param content 消息内容
     *             * @param <T>     消息类型
     */
    public static <T> void publish(NotifyTypeEnum type, T content) {}
}