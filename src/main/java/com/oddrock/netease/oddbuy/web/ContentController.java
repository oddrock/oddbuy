package com.oddrock.netease.oddbuy.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.entity.Person;
import com.oddrock.netease.oddbuy.service.ContentService;

@Controller
public class ContentController {
	private static Logger logger = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;

	@RequestMapping("/public")
	public ModelAndView publish(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("public");
		return mv;
	}

	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Integer id = Integer.valueOf(request.getParameter("id"));
		Content content = contentService.get(id);
		mv.addObject("product", content);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("show");
		return mv;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Integer id = Integer.valueOf(request.getParameter("id"));
		Content content = contentService.get(id);
		mv.addObject("product", content);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("edit");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		List<Content> productList = contentService.findAllList();
		mv.addObject("productList", productList);
		mv.setViewName("index");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(Content content, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		contentService.update(content);
		logger.warn(content);
		mv.addObject("product", content);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("editSubmit");
		return mv;
	}

	@RequestMapping("/publicSubmit")
	public ModelAndView publicSubmit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Content content = new Content();
		content.setImage(request.getParameter("image"));
		content.setSummary(request.getParameter("summary"));
		content.setPrice(Long.valueOf(request.getParameter("price")));
		content.setDetail(request.getParameter("detail"));
		content.setTitle(request.getParameter("title"));
		contentService.insert(content);
		mv.addObject("product", content);
		mv.setViewName("publicSubmit");
		return mv;
	}
}
