package com.oddrock.netease.oddbuy.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oddrock.netease.oddbuy.entity.Content;
import com.oddrock.netease.oddbuy.entity.Person;
import com.oddrock.netease.oddbuy.entity.Trx;
import com.oddrock.netease.oddbuy.impure.Account;
import com.oddrock.netease.oddbuy.service.ContentService;
import com.oddrock.netease.oddbuy.service.TrxService;

@Controller
public class ContentController {
	private static Logger logger = Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;
	@Autowired
	private TrxService trxService;

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
	
	@RequestMapping("/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Integer id = Integer.valueOf(request.getParameter("id"));
		contentService.delete(id);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		List<Content> productList = contentService.findAllList();
		mv.addObject("productList", productList);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/addCart")
	public ModelAndView addCart(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Long id = Long.valueOf(request.getParameter("productId"));
		Long price = Long.valueOf(request.getParameter("productPrice"));
		
		HttpSession session = request.getSession();
		String title = (String)request.getParameter("productTitle");
		Content cartProduct = new Content();
		cartProduct.setId(id);
		cartProduct.setTitle(title);
		cartProduct.setPrice(price);
		cartProduct.setBuyNum(1);
		Map<Long, Content> cart = (Map<Long, Content>)session.getAttribute("cart");
		if(cart==null) {
			cart = new HashMap<Long, Content>();
			session.setAttribute("cart", cart);
		}
		if(cart.containsKey(id)) {
			cartProduct = cart.get(id);
			cartProduct.setBuyNum(cartProduct.getBuyNum()+1);
		}else {
			cart.put(id, cartProduct);
		}
		
		Content content = contentService.get(id);
		mv.addObject("product", content);
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("show");
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
		return mv;
	}
	
	@RequestMapping("/settleAccount")
	public ModelAndView settleAccount(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Map<Long, Content> cart = (Map<Long, Content>)session.getAttribute("cart");
		List<Content> cartProductList = new ArrayList<Content>();
		if(cart!=null) {
			for(Content c : cart.values()) {
				cartProductList.add(c);
			}
		}
		mv.addObject("cartProductList", cartProductList);
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("settleAccount");
		return mv;
	}
	
	@RequestMapping("/buy")
	public ModelAndView buy(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		Map<Long, Content> cart = (Map<Long, Content>)session.getAttribute("cart");
		BigInteger time = BigInteger.valueOf(System.currentTimeMillis());
		Long personId = user.getId();
		if(cart!=null) {
			for(Content content : cart.values()) {
				for(int i=0;i<content.getBuyNum();i++) {
					Trx trx = new Trx();
					trx.setContentId(content.getId());
					trx.setPrice(content.getPrice());
					trx.setPersonId(personId);
					trx.setTime(time);
					trxService.insert(trx);
				}
				
			}
			session.removeAttribute("cart");
		}
		
		mv.addObject("user", user);
		List<Content> productList = contentService.findAllList();
		mv.addObject("productList", productList);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request, HttpServletResponse response,
			Content content,MultipartFile file, @Param("imageNew") String imageNew) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		logger.warn("前："+content);
		if (!file.isEmpty()) {			
            String originalFileName = file.getOriginalFilename();
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
            String relavantFilePath = "/upload/"+newFileName;
            String newFilePath = request.getServletContext().getRealPath(relavantFilePath);
            // 新的图片
            File newFile = new File(newFilePath);
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            content.setImage("upload/"+newFileName);
        }else if(imageNew!=null && imageNew.trim().length()>0){
        	content.setImage(imageNew);
        }
		logger.warn("后："+content);
		contentService.update(content);
		mv.addObject("product", content);
		HttpSession session = request.getSession();
		Person user = (Person) session.getAttribute("user");
		mv.addObject("user", user);
		mv.setViewName("editSubmit");
		return mv;
	}

	@RequestMapping("/publicSubmit")
	public ModelAndView publicSubmit(HttpServletRequest request, HttpServletResponse response,MultipartFile file) throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		Content content = new Content();
		content.setSummary(request.getParameter("summary"));
		content.setPrice(Long.valueOf(request.getParameter("price")));
		content.setDetail(request.getParameter("detail"));
		content.setTitle(request.getParameter("title"));
		String image = (String)request.getParameter("image");
		if (!file.isEmpty()) {		
            String originalFileName = file.getOriginalFilename();
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
            String relavantFilePath = "/upload/"+newFileName;
            String newFilePath = request.getServletContext().getRealPath(relavantFilePath);
            // 新的图片
            File newFile = new File(newFilePath);
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            content.setImage("upload/"+newFileName);
        }else if(image!=null && image.trim().length()>0){
        	content.setImage(image);
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
