package com.github.paicoding.forum.service.chatai.service.impl.xunfei;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.paicoding.forum.api.model.vo.chat.ChatItemVo;
import com.github.paicoding.forum.core.util.JsonUtil;
import com.github.paicoding.forum.service.chatai.constants.ChatConstants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 主体来自讯飞官方java sdk
 *
 * <a href="https://www.xfyun.cn/doc/spark/Web.html#_1-%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E"/>
 * */
@Slf4j
@Setter
@Component
public class XunFeiIntegration {

    @Autowired
    private XunFeiConfig xunFeiConfig;

    @Getter
    private OkHttpClient okHttpClient;

    @PostConstruct
    public void init()  {}

    public String buildXunFeiUrl()  { return null; }

    /**
     * 构建授权url
     *
     * @param hostUrl
     * @param apikey
     * @param apisecret
     * @return
     * @throws Exception
     */
    public String getAuthorizationUrl(String hostUrl, String apikey, String apisecret) throws Exception  { return null; }

    public String buildSendMsg(String uid, String question)  { return null; }

    /**
     * 结合上下文的回答
     *
     * @param uid
     * @param items
     * @return
     */
    public String buildSendMsg(String uid, List<ChatItemVo> items)  { return null; }

    /**
     * 构建提问消息
     *
     * @param item
     * @return
     */
    private static JsonArray toText(ChatItemVo item)  { return null; }

    public ResponseData parse2response(String text)  { return null; }


    @Component
    @ConfigurationProperties(prefix = "xunfei")
    @Data
    public static class XunFeiConfig {
        public String hostUrl = "http://spark-api.xf-yun.com/v1.1/chat";
        public String appId = "";
        public String apiKey = "";
        public String apiSecret = "";
        public String  APIPassword = "";
        // 指定访问的领域,general指向V1.5版本 generalv2指向V2版本。注意：不同的取值对应的url也不一样！
        public String domain = "general";
    }

    @Data
    public static class ResponseData {
        private Header header;
        private Payload payload;

        public boolean successReturn()  { return false; }

        /**
         * 首次返回结果
         *
         * @return
         */
        public boolean firstResonse()  { return false; }

        /**
         * 判断是否是最后一次返回的结果
         *
         * @return
         */
        public boolean endResponse()  { return false; }
    }

    @Data
    public static class Header {
        /**
         * 错误码，0表示正常，非0表示出错；详细释义可在接口说明文档最后的错误码说明了解
         */
        private int code;
        /**
         * 会话是否成功的描述信息
         */
        private String message;
        /**
         * 会话的唯一id，用于讯飞技术人员查询服务端会话日志使用,出现调用错误时建议留存该字段
         */
        private String sid;
        /**
         * 会话状态，取值为[0,1,2]；0代表首次结果；1代表中间结果；2代表最后一个结果
         */
        private String status;
    }

    @Data
    public static class Payload {
        private Choices choices;
        private Usage usage;
    }

    @Data
    public static class Choices {
        /**
         * 文本响应状态，取值为[0,1,2]; 0代表首个文本结果；1代表中间文本结果；2代表最后一个文本结果
         */
        private int status;
    /**
         * 返回的数据序号，取值为[0,9999999]
         */
        private int seq;

        private List<ChoicesText> text;
    }

    @Data
public static class ChoicesText {
        /**
         * 结果序号，取值为[0,10]; 当前为保留字段，开发者可忽略
         */
        private int index;
        /**
         * 角色标识，固定为assistant，标识角色为AI
         */
        private String role;
        /**
         * AI的回答内容
         */
        private String content;

        private String reasoning_content;
    }

    @Data
    public static class Usage {
        private UsageText text;
    }

    @Data
    public static class UsageText {
        /**
         * 保留字段，可忽略
         */
        @JsonAlias("question_tokens")
        private int questionTokens;
        /**
         * 包含历史问题的总tokens大小
         */
        @JsonAlias("prompt_tokens")
        private int promptTokens;
        /**
         * 回答的tokens大小
         */
        @JsonAlias("completion_tokens")
        private int completionTokens;
        /**
         * prompt_tokens和completion_tokens的和，也是本次交互计费的tokens大小
         */
        @JsonAlias("total_tokens")
        private int totalTokens;
    }
}
