package com.github.paicoding.forum.service.article.conveter;

import com.github.paicoding.forum.api.model.vo.article.dto.ArticlePayInfoDTO;
import com.github.paicoding.forum.api.model.vo.user.dto.UserPayCodeDTO;
import com.github.paicoding.forum.service.article.repository.entity.ArticlePayRecordDO;

import java.util.Map;

/** */
public class PayConverter {

    public static ArticlePayInfoDTO toPay(ArticlePayRecordDO record) { return null; }


    /**
     * 格式化收款码
     *
     * @return key: 渠道   value: 收款二维码base64格式
     */
    public static Map<String, String> formatPayCode(String dbCode) { return null; }

    public static Map<String, UserPayCodeDTO> formatPayCodeInfo(String dbCode) { return null; }


    public static String genQrCode(String txt) { return null; }
}