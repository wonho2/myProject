package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

@Controller
public class MemberController {

	private Logger log = Logger.getLogger(this.getClass());

	//의존 관계 설정
	@Resource
	private MemberService memberService;

	//자바빈(VO)초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	//아이디 중복 체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("id") String id){

		if(log.isDebugEnabled()) {
			log.debug("<<id>> : " + id);
		}

		Map<String,String> map = new HashMap<String,String>();

		MemberVO member = memberService.selectCheckMember(id);
		if(member!=null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}else {
			if(!Pattern.matches("^[A-Za-z0-9+]{4,12}$", id)) {
				//패턴 불일치
				map.put("result", "notMatchPattern");
			}else {
				//아이디 미중복
				map.put("result", "idNotFound");
			}
		}
		return map;
	}
	//회원가입 폼 호출
	@RequestMapping(value="/member/registerUser.do",method=RequestMethod.GET)
	public String form() {
		return "memberRegister";
	}

	//회원 등록 데이터 전송
	@RequestMapping(value="/member/registerUser.do",method=RequestMethod.POST)
	public String submit(@Valid MemberVO memberVO,
			BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<회원 가입>> : " + memberVO);
		}

		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}

		//회원 가입
		memberService.insertMember(memberVO);

		return "redirect:/main/main.do";
	}

	//로그인 폼 호출
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	//로그인 폼에서 전송된 데이터 처리
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@Valid MemberVO memberVO,
			BindingResult result,
			HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<회원 로그인>> : " + memberVO);
		}

		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || 
				result.hasFieldErrors("passwd")) {
			return formLogin();
		}

		//로그인 체크(입력한 id와 비밀번호가 DB에 저장된 id와 비밀번호와 일치하는지 여부)
		try {
			MemberVO member = memberService.selectCheckMember(
					memberVO.getId());
			boolean check = false;

			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPassword(
						memberVO.getPasswd());
			}
			if(check) {//인증 성공, 로그인 처리
				//회원번호 저장
				session.setAttribute("user_num", member.getMem_num());
				//회원 아이디 저장
				session.setAttribute("user_id", member.getId());

				return "redirect:/main/main.do";

			}else {//인증 실패
				throw new AuthCheckException();
			}
		}catch(AuthCheckException e) {
			//에러 메시지 처리
			result.reject("invalidIdOrPassword");
			//인증 실패로 로그인 폼 호출
			return formLogin();
		}
	}
}
