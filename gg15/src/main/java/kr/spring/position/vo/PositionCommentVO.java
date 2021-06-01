package kr.spring.position.vo;

import kr.spring.util.DurationFromNow;

public class PositionCommentVO 
{
	private int poc_num; // 댓글 번호
	private int pos_num; // 게시물 번호
	private int mem_num; // 회원 번호
	private String mem_nick; // 회원 닉네임
	private String poc_content; // 댓글 내용
	private String poc_date; // 댓글 작성 날짜
	
	public int getPoc_num() {
		return poc_num;
	}
	public void setPoc_num(int poc_num) {
		this.poc_num = poc_num;
	}
	public int getPos_num() {
		return pos_num;
	}
	public void setPos_num(int pos_num) {
		this.pos_num = pos_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(Integer mem_num) {
		if(mem_num == null) return;
		this.mem_num = mem_num;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getPoc_content() {
		return poc_content;
	}
	public void setPoc_content(String poc_content) {
		if(poc_content == null) return;
		this.poc_content = poc_content;
	}
	public String getPoc_date() {
		return poc_date;
	}
	public void setPoc_date(String poc_date) {
		this.poc_date = DurationFromNow.getTimeDiffLabel(poc_date);
	}
}
