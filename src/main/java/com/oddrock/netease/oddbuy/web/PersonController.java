package com.oddrock.netease.oddbuy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oddrock.netease.oddbuy.service.PersonService;

@Controller
public class PersonController {
	private static Logger logger = Logger.getLogger(PersonController.class);
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/login")
    public String userList(@RequestParam("userName") String userName, 
            @RequestParam("password") String password, 
            ModelMap map, HttpServletRequest request) {
        HttpSession session = request.getSession();
        logger.warn(userName+"正在登录...");
        // 如果Session中已经有用户名，就不必再登录，直接跳转到user界面
        if (session.getAttribute("userName") == null) {  
            // 如果Session中没有用户名，则进行检查，通过则将用户名写入session，再跳转到user界面
            if(personService.checkUser(userName, password)) {
                map.addAttribute("userName", userName);
                session.setAttribute("userName", userName);
                logger.warn(userName+"登录成功。");
                return "biz/user";
            }else {
                // 否则跳转到错误页面
            	logger.warn(userName+"登录失败！");
                return "error";
            }
        }else {
            map.addAttribute("userName", session.getAttribute("userName"));
            logger.warn(userName+"之前已登录，不必重复登录。");
			return "biz/user";
        }
    }
}
