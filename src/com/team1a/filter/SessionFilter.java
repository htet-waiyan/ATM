package com.team1a.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="SessionFilter")
public class SessionFilter implements Filter {
	protected ServletContext servletContext;
	protected HttpSession session;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		
		session=request.getSession(true);
		if(!isAuth()){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		servletContext=config.getServletContext();
	}
	
	public boolean isAuth(){
		if(session.isNew()){
			return false;
		}
		else
			return true;
	}
}
