package com.oddrock.netease.oddbuy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.entity.Person;
import com.oddrock.netease.oddbuy.service.ContentService;
import com.oddrock.netease.oddbuy.service.PersonService;

@Controller
public class PersonController {
	private static Logger logger = Logger.getLogger(PersonController.class);
	@Autowired
	private PersonService personService;
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/welcome")
    public ModelAndView  welcome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
        Person user = (Person)session.getAttribute("user");
        if(userName==null) {
        	mv.setViewName("login");
        }else {
        	if(user==null) {
        		List<Person> list = personService.getUser(userName);
        		user = list.get(0);
                session.setAttribute("user", user);
                logger.warn(userName+"之前已登录，不必重复登录。");
        	}
        	List<Content> productList = contentService.findAllList();
    		mv.addObject("productList", productList);
        	mv.setViewName("index");
        }
		return mv;
	}
	
	@RequestMapping("/login")
    public ModelAndView  login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        Person user = (Person)session.getAttribute("user");
        ModelAndView mv = new ModelAndView();
        
        if(userName==null) {	// 如果Session中没有用户名，则进行检查，通过则将用户名写入session，再跳转到user界面
        	userName = request.getParameter("userName");
        	String password = request.getParameter("password");
        	List<Person> list = personService.checkUser(userName, password);
            if(list.size()>0) {
                session.setAttribute("userName", userName);
                user = list.get(0);
                session.setAttribute("user", user);
                logger.warn(userName+"登录成功。");
            }else {
                // 否则跳转到错误页面
            	logger.warn(userName+"登录失败！");
            	mv.addObject("errorTip","用户名或密码错误！");
            	logger.warn("222");
            	mv.setViewName("login");
                return mv;
            }
        }else {					// 如果Session中已经有用户名，就不必再登录，直接跳转到user界面
        	if(user==null) {
        		List<Person> list = personService.getUser(userName);
        		user = list.get(0);
        	}
        	logger.warn(userName+"正在登录，但之前已登录，不必重复登录。");
        }
        
        mv.addObject("user", user);
        List<Content> productList = contentService.findAllList();
        mv.addObject("productList", productList);
        mv.setViewName("index");
		return mv;
    }
	
	@RequestMapping("/logout")
    public ModelAndView  logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("userName");
		session.removeAttribute("user");
		session.removeAttribute("cart");
		mv.setViewName("login");
		return mv;
	}
}
