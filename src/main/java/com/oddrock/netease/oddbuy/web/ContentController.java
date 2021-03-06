package com.oddrock.netease.oddbuy.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oddrock.netease.oddbuy.entity.BuyItem;
import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.entity.Person;
import com.oddrock.netease.oddbuy.entity.Trx;
import com.oddrock.netease.oddbuy.impure.Account;
import com.oddrock.netease.oddbuy.service.ContentService;
import com.oddrock.netease.oddbuy.service.TrxService;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Controller
public class ContentController {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;
	@Autowired
	private TrxService trxService;
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void handleException(MaxUploadSizeExceededException ex, HttpServletResponse response)  {
		StringBuffer sb = new StringBuffer();
		sb.append("<script language='javascript'>alert('");
		sb.append("文件大小不应大于" + ((MaxUploadSizeExceededException) ex).getMaxUploadSize() / 1024 + "KB");
		sb.append("！');history.go(-1);</script>");
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().println(sb.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	@RequestMapping("/public")
	public ModelAndView publish(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("public");
		return mv;
	}

	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Long id = Long.valueOf(request.getParameter("id"));
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
		Long id = Long.valueOf(request.getParameter("id"));
		Content content = contentService.get(id);
		mv.addObject("product", content);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("edit");
		return mv;
	}
	
	@RequestMapping("/api/delete")
	@ResponseBody
	public Map<String,Object> apiDelete(String id) {
		Integer idInt = Integer.valueOf(id);
		contentService.delete(idInt);
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("code", 200);
        map.put("message", "删除成功！！！");  
        map.put("result", true);
		return map;
	}


	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		List<Content> productList = contentService.findAllList();
		mv.addObject("productList", productList);
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/settleAccount")
	public ModelAndView settleAccount(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("settleAccount");
		return mv;
	}
	
	@RequestMapping("/api/buy")
	public @ResponseBody Map<String,Object> apiBuy(HttpServletRequest request, @RequestBody List<BuyItem> items) {
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		BigInteger time = BigInteger.valueOf(System.currentTimeMillis());
		Long personId = user.getId();
		for(BuyItem item: items) {
			Content content = contentService.get(item.getId());
			if(content!=null) {
				for (int i = 0; i < item.getNumber(); i++) {
					Trx trx = new Trx();
					trx.setContentId(item.getId());
					trx.setPrice(content.getPrice());
					trx.setPersonId(personId);
					trx.setTime(time);
					trxService.insert(trx);
				}
			}
		}
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("code", 200);
        map.put("message", "购买成功！！！");  
        map.put("result", true);
		return map;
	}

	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request, HttpServletResponse response,@Valid  Content content, Errors errors,
			MultipartFile file, @Param("imageNew") String imageNew) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		if (errors.hasErrors()) {
			mv.addObject("errors", errors);
			mv.addObject("product", content);
			mv.setViewName("edit");
	        return mv;
	    }
		if (!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			// 新的图片名称
			String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			String relavantFilePath = "/upload/" + newFileName;
			String newFilePath = request.getServletContext().getRealPath(relavantFilePath);
			// 新的图片
			File newFile = new File(newFilePath);
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
			//content.setImage(newFilePath);
			String projectRoot = request.getServletContext().getContextPath();
			content.setImage(projectRoot+"/upload/" + newFileName);
		} else if (imageNew != null && imageNew.trim().length() > 0) {
			content.setImage(imageNew);
		}
		contentService.update(content);
		mv.addObject("product", content);
		mv.setViewName("editSubmit");
		return mv;
	}

	@RequestMapping("/publicSubmit")
	public ModelAndView publicSubmit(HttpServletRequest request, HttpServletResponse response, 
			@Valid Content content, Errors errors, MultipartFile file)
			throws IllegalStateException, IOException {
		
		ModelAndView mv = new ModelAndView();
		if (errors.hasErrors()) {
			mv.addObject("errors", errors);
			mv.addObject("product", content);
			mv.setViewName("public");
	        return mv;
	    }
		if (!file.isEmpty()) {
			String originalFileName = file.getOriginalFilename();
			// 新的图片名称
			String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
			String relavantFilePath = "/upload/" + newFileName;
			String newFilePath = request.getServletContext().getRealPath(relavantFilePath);
			// 新的图片
			File newFile = new File(newFilePath);
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
			String projectRoot = request.getServletContext().getContextPath();
			content.setImage(projectRoot+"/upload/" + newFileName);
		} 
		contentService.insert(content);
		mv.addObject("product", content);
		mv.setViewName("publicSubmit");
		return mv;
	}

	@RequestMapping("/account")
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		List<Account> buyList = trxService.findAllList();
		mv.addObject("buyList", buyList);
		mv.setViewName("account");
		return mv;
	}
	
	
}
