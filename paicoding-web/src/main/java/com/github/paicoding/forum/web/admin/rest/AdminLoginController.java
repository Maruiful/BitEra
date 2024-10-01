package com.github.paicoding.forum.web.admin.rest;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import com.github.paicoding.forum.api.model.vo.ResVo;
import com.github.paicoding.forum.api.model.vo.constants.StatusEnum;
import com.github.paicoding.forum.api.model.vo.user.dto.BaseUserInfoDTO;
import com.github.paicoding.forum.core.permission.Permission;
import com.github.paicoding.forum.core.permission.UserRole;
import com.github.paicoding.forum.core.util.SessionUtil;
import com.github.paicoding.forum.service.user.service.AuthorWhiteListService;
import com.github.paicoding.forum.service.user.service.LoginService;
import com.github.paicoding.forum.service.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 文章后台
 *
 */
@RestController
@Api(value = "后台登录登出管理控制器", tags = "后台登录")
@RequestMapping(path = {"/api/admin", "/admin"})
public class AdminLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginOutService;

    @Autowired
    private AuthorWhiteListService articleWhiteListService;

    /**
     * 后台用户名 & 密码的方式登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = {"login"})
    public ResVo<BaseUserInfoDTO> login(HttpServletRequest request,
                                        HttpServletResponse response)  { return null; }

    /**
     * 判断是否有登录
     *
     * @return
     */
    @RequestMapping(path = "isLogined")
    public ResVo<Boolean> isLogined()  { return null; }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("info")
    public ResVo<BaseUserInfoDTO> info()  { return null; }

    /**
     * 登出
     *
     * @param response
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping("logout")
    public ResVo<Boolean> logOut(HttpServletResponse response)  { return null; }
}
