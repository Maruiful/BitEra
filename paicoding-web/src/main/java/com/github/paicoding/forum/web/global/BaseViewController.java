package com.github.paicoding.forum.web.global;

import com.github.paicoding.forum.api.model.vo.PageParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 全局属性配置
 * */
public class BaseViewController {
    @Autowired
    protected GlobalInitService globalInitService;

    public PageParam buildPageParam(Long page, Long size)  { return null; }

//
//  推荐使用它替代 GlobalViewInterceptor 中的全局属性设置
//    /**
//     * 全局属性配置
//     *
//     * @param model
//     */
//    @ModelAttribute
//    public void globalAttr(Model model)  {}
}
