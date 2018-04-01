package com.oddrock.netease.oddbuy.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oddrock.netease.oddbuy.entity.Person;


@Aspect
@Component
public class LoggingAspect {
	private static Logger logger = Logger.getLogger(LoggingAspect.class);
    @Before("execution(* com.oddrock.netease.oddbuy.web.*.*(..)) && args(request, ..)")
    private void arithmeticDoLog(JoinPoint jp,HttpServletRequest request) {
        /*RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;  
        HttpServletRequest request = sra.getRequest();  */
    	HttpSession session = request.getSession();
        Person user = (Person)session.getAttribute("user");
    	logger.warn(jp.toString());
    	logger.warn("user is:" + user);
    }
}
