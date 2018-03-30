package com.oddrock.netease.oddbuy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/login")
    public ModelAndView  login(@RequestParam("userName") String userName, 
            @RequestParam("password") String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        logger.warn(userName+"正在登录...");
        List<Person> list = null;
        // 如果Session中已经有用户名，就不必再登录，直接跳转到user界面
        if (session.getAttribute("userName") == null) {  
            // 如果Session中没有用户名，则进行检查，通过则将用户名写入session，再跳转到user界面
        	list = personService.checkUser(userName, password);
            if(list.size()>0) {
                session.setAttribute("userName", userName);
                logger.warn(userName+"登录成功。");
            }else {
                // 否则跳转到错误页面
            	logger.warn(userName+"登录失败！");
            	mv.setViewName("error");
                return mv;
            }
        }else {
        	userName = (String)session.getAttribute("userName");
            logger.warn(userName+"之前已登录，不必重复登录。");
            
        }
        
        if(list==null || list.size()==0) {
        	list = personService.getUser(userName);
        }
        mv.addObject("user", list.get(0));
        mv.addObject("userName", userName);
        mv.setViewName("test");
		return mv;
    }
	
	@RequestMapping("/logout")
    public ModelAndView  logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.removeAttribute("userName");
		mv.setViewName("login");
		return mv;
	}
}
