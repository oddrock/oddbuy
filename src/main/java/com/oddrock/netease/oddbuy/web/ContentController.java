package com.oddrock.netease.oddbuy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.service.ContentService;


@Controller
public class ContentController {
	private static Logger logger = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/public")
    public ModelAndView  publish(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("public");
		return mv;
    }
	
	@RequestMapping("/publicSubmit")
    public ModelAndView  publicSubmit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Content content = new Content();
		content.setIcon(request.getParameter("image"));
		content.setDigest(request.getParameter("summary"));
		content.setPrice(Long.valueOf(request.getParameter("price")));
		content.setText(request.getParameter("detail"));
		content.setTitle(request.getParameter("title"));
		contentService.insert(content);
		mv.addObject("product", content);
		mv.setViewName("publicSubmit");
		return mv;
    }
}
