package kr.spring.board.vo;

import java.sql.Date;

public class board_replyVO {
	
	private int mem_num;//회원번호
	private int bor_num;//댓글번호
	private String bor_content;//댓글 내용
	private Date bor_date;//댓글 작성일
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getBor_num() {
		return bor_num;
	}
	public void setBor_num(int bor_num) {
		this.bor_num = bor_num;
	}
	public String getBor_content() {
		return bor_content;
	}
	public void setBor_content(String bor_content) {
		this.bor_content = bor_content;
	}
	public Date getBor_date() {
		return bor_date;
	}
	public void setBor_date(Date bor_date) {
		this.bor_date = bor_date;
	}
	
	
	
	
}
