package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.manualtool.service.ManualtoolService;
import kr.spring.manualtool.vo.ManualtoolVO;

public class ManualtoolWriterInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private ManualtoolService manualtoolService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception {
		//해당 게시물 번호 구하기
		int man_num = Integer.parseInt(request.getParameter("man_num"));
		ManualtoolVO manualtoolVO = manualtoolService.selectManualtool(man_num);
		
		//현재 로그인 되어있는 회원 번호 구하기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		//로그인 회원번호와 게시물을 작성한 회원 번호가 일치하지 않은 경우
		if(user_num != manualtoolVO.getMem_num())
		{
			//포워드 방식으로 View 호출
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			return false;
		}
		
		return true;
	}

}
