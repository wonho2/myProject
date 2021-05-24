package kr.spring.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	//회원관리
	//회원 번호 구하기
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	//회원 등록
	@Insert("INSERT INTO member (mem_num,mem_id) VALUES (#{mem_num},#{id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_pw,mem_nick,mem_phone,mem_email) VALUES (#{mem_num},#{name},#{passwd},#{nick},#{phone},#{email})")
	public void insertMember_detail(MemberVO member);
	//아이디 중복 체크 및 로그인 체크
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.mem_pw FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{id}")
	public MemberVO selectCheckMember(String id);
}
