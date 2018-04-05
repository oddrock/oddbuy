package com.oddrock.netease.oddbuy.aop;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAuth implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("userName");
        if( user == null ){
        	String projectRoot = request.getServletContext().getContextPath();
            response.sendRedirect(projectRoot+"/login");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler, ModelAndView modelAndView) throws Exception {}
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
    		Object handler, Exception ex) throws Exception {}
}