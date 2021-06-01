package kr.spring.manualtool.vo;

import kr.spring.util.DurationFromNow;

public class ManualtoolCommentVO {
	
	private int mar_num;	//댓글 고유 번호
	private int man_num;	//댓글이 소속된 게시물의 고유 번호
	private int mem_num;	//작성자 고유 회원 번호
	private String mem_nick;	//작성자 닉네임
	private String mar_content;	//댓글 내용
	private String mar_date;	//댓글 작성일
	
	public int getMar_num() {
		return mar_num;
	}
	public void setMar_num(int mar_num) {
		this.mar_num = mar_num;
	}
	public int getMan_num() {
		return man_num;
	}
	public void setMan_num(int man_num) {
		this.man_num = man_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMar_content() {
		return mar_content;
	}
	public void setMar_content(String mar_content) {
		this.mar_content = mar_content;
	}
	public String getMar_date() {
		return mar_date;
	}
	public void setMar_date(String mar_date) {
		this.mar_date = DurationFromNow.getTimeDiffLabel(mar_date);
	}
	@Override
	public String toString() {
		return "ManualtoolCommentVO [mar_num=" + mar_num + ", man_num=" + man_num + ", mem_num=" + mem_num + ", mem_nick="
				+ mem_nick + ", mar_content=" + mar_content + ", mar_date=" + mar_date + "]";
	}

}
