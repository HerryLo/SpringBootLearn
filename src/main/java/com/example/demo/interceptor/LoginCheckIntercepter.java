package com.example.demo.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.pojo.Result;
import com.example.demo.utils.JwtsUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 使用spring框架自带的拦截器进行token拦截
 */
@Slf4j
@Component
public class LoginCheckIntercepter implements HandlerInterceptor {
    /**
     * 预处理：在 Controller 执行前调用
     * 返回 true：继续执行；返回 false：中断请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginCheckIntercepter preHandle");

        String url = request.getRequestURL().toString();
        // 登录操作不做过滤
        if(url != null && url.contains("/login")) {
            log.info("登陆操作，不做过滤");
            return true;
        }

        // 判断token是否存在
        String token = request.getHeader("token");
        if(token == null) {
            log.info("请求头token为空，返回未登录信息");
            Result err = Result.error("NO_LOGIN");
            String notLogin = JSONObject.toJSONString(err);
            response.getWriter().write(notLogin);
            return false;
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
            return false;
        }
        return true;
    }

    /**
     * 后处理：在 Controller 执行后，视图渲染前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完成处理：视图渲染后调用（用于资源清理）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
