package com.example.demo.filter;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.pojo.Result;
import com.example.demo.utils.JwtsUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 使用Servlet中的过滤器进行token拦截，当前过滤器注释未生效
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("UserCheckFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("UserCheckFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        // 登录操作不做过滤
        if(url != null && url.contains("/login")) {
            log.info("登陆操作，不做过滤");
            filterChain.doFilter(request, response);
            return;
        }

        // 判断token是否存在
        String token = request.getHeader("token");
        if(token == null) {
            log.info("请求头token为空，返回未登录信息");
            Result err = Result.error("NO_LOGIN");
            String notLogin = JSONObject.toJSONString(err);
            response.getWriter().write(notLogin);
            return;
        }

        // 解析token
        try {
            JwtsUtil.parseJwts(token);
        }catch (Exception e) {
            log.info("解析token失败，返回未登录信息");
            e.printStackTrace();
            Result err = Result.error("NO_LOGIN");
            String notLogin = JSONObject.toJSONString(err);
            response.getWriter().write(notLogin);
            return;
        }

        log.info("token合法，不做过滤");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("UserCheckFilter destroy");
        Filter.super.destroy();
    }
}
