package com.oddrock.netease.oddbuy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckFilter implements Filter {

	@Override
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        System.out.println(req.getRequestURI());
        /*
        if("/simpleweb/".equals(req.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }*/
        if (session.getAttribute("userName") == null) {          
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("/WEB-INF/jsp/error.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
