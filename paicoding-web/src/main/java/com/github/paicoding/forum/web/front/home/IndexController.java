package com.github.paicoding.forum.web.front.home;

import com.github.paicoding.forum.web.front.home.helper.IndexRecommendHelper;
import com.github.paicoding.forum.web.front.home.vo.IndexVo;
import com.github.paicoding.forum.web.global.BaseViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/** */
@Controller
public class IndexController extends BaseViewController {
    @Autowired
    private IndexRecommendHelper indexRecommendHelper;

    @GetMapping(path = {"/", "", "/index", "/login"})
    public String index(Model model, HttpServletRequest request)  { return null; }
}
