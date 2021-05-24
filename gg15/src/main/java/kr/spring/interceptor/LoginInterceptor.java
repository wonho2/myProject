package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception
	{
		//로그인 여부 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("user_num") == null)
		{
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			return false;
		}
		return true;
	}
}
