package com.github.paicoding.forum.core.util;

import com.github.paicoding.forum.api.model.context.ReqInfoContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** */
@Slf4j
public class SessionUtil {
    private static final int COOKIE_AGE = 5 * 86400;

    public static String buildSetCookieString(Cookie cookie)  { return null; }

    public static Cookie newCookie(String key, String session)  {
        return newCookie(key, session, "/", COOKIE_AGE);
    }

    public static Cookie newCookie(String key, String session, String path, int maxAge)  { return null; }

    public static Cookie newCookie(String key, String session, String domain, String path, int maxAge) {
        // 移除端口号
        domain = removePortFromHost(domain);
        Cookie cookie = new Cookie(key, session);
        if (StringUtils.isNotBlank(domain)) {
            if (EnvUtil.isPro() && "127.0.0.1".equals(domain)) {
                // 说明：对于使用nginx进行转发的场景，需要设置： proxy_set_header X-Forwarded-Host $host; 否则会导致这里拿到的host为 127.0.0.1
                log.info("登录的来源：{}", ReqInfoContext.getReqInfo());
                domain = "paicoding.com";
            }
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    /**
     * 从host中移除端口号
     *
     * @param host 包含端口号的host，如 "localhost:8080"
     * @return 移除端口号后的host，如 "localhost"
     */
    private static String removePortFromHost(String host)  { return null; }

    public static Cookie delCookie(String key)  { return null; }

    /**
     * 移除所有相关的Cookie
     *
     * @param key
     */
    public static void delCookies(String key) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Arrays.stream(request.getCookies()).filter(ck -> Objects.equals(ck.getName(), key)).forEach(ck -> {
            ck.setMaxAge(0);
            if (response != null) {
                response.addCookie(ck);
            }
        });
    }

    public static Cookie delCookie(String key, String host)  { return null; }

    public static Cookie delCookie(String key, String host, String path)  { return null; }

    public static void delCookie(Cookie ck)  {}

    /**
     * 根据key查询cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie findCookieByName(HttpServletRequest request, String name)  { return null; }

    public static List<Cookie> findCookiesByName(HttpServletRequest request, String name)  { return null; }


    public static String findCookieByName(ServerHttpRequest request, String name) {
        List<String> list = request.getHeaders().get("cookie");
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        for (String sub : list) {
            String[] elements = StringUtils.split(sub, ";");
            for (String element : elements) {
                String[] subs = StringUtils.split(element, "=");
                if (subs.length == 2 && StringUtils.equalsAnyIgnoreCase(subs[0].trim(), name)) {
                    return subs[1].trim();
                }
            }
        }
        return null;
    }
}