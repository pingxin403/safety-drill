package com.hyp.learn.shiro.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.hyp.learn.shiro.business.consts.SessionConst;
import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.business.service.SysUserService;
import com.hyp.learn.shiro.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.interceptor
 * hyp create at 20-3-29
 **/
@Component
@Slf4j
public class RememberAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return true;
        }
        Session session = subject.getSession(true);
        if (session.getAttribute(SessionConst.USER_SESSION_KEY) != null) {
            return true;
        }
        if (!subject.isRemembered()) {
            log.warn("未设置“记住我”,跳转到登录页...");
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }
        try {
            long userId = Long.parseLong(subject.getPrincipal().toString());
            User user = new User(userService.getById(userId));
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), PasswordUtil.decrypt(user.getPassword(), user.getUsername()), true);
            //移除线程中的subject
            ThreadContext.remove(ThreadContext.SUBJECT_KEY);
            subject.login(token);
            session.setAttribute(SessionConst.USER_SESSION_KEY, user.getSysUser());
            log.info("[{}] - 已自动登录", user.getUsername());

        } catch (Exception e) {
            log.error("自动登录失败", e);
            response.sendRedirect(request.getContextPath() + "/passport/login");
            return false;
        }

        return true;

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
