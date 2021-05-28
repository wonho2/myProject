package kr.spring.position.vo;

import kr.spring.util.DurationFromNow;

public class PositionCommentVO 
{
	private int poc_num;
	private int pos_num;
	private int mem_num;
	private String mem_nick;
	private String poc_content;
	private String poc_date;
	private int poc_fav; // 댓글의 추천 수
	private int click_favUp; // 추천을 누른 회원 번호
	private int click_favDown; // 비추천을 누른 회원 번호
	
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
	// session.user_num == null 인 경우
	public void setMem_num(Integer mem_num) {
		if(mem_num == null) {
			return;
		}
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
	// jsp 페이지 ${poc_content} == null 인 경우
	public void setPoc_content(String poc_content) {
		if(poc_content == null) {
			return;
		}
		this.poc_content = poc_content;
	}
	public String getPoc_date() {
		return poc_date;
	}
	public void setPoc_date(String poc_date) {
		this.poc_date = DurationFromNow.getTimeDiffLabel(poc_date);
	}
	public int getPoc_fav() {
		return poc_fav;
	}
	public void setPoc_fav(int poc_fav) {
		this.poc_fav = poc_fav;
	}
	public int getClick_favUp() {
		return click_favUp;
	}
	public void setClick_favUp(int click_favUp) {
		this.click_favUp = click_favUp;
	}
	public int getClick_favDown() {
		return click_favDown;
	}
	public void setClick_favDown(int click_favDown) {
		this.click_favDown = click_favDown;
	}
	
	@Override
	public String toString() {
		return "PositionCommentVO [poc_num=" + poc_num + ", pos_num=" + pos_num + ", mem_num=" + mem_num + ", mem_nick="
				+ mem_nick + ", poc_content=" + poc_content + ", poc_date=" + poc_date + ", poc_fav=" + poc_fav
				+ ", click_favUp=" + click_favUp + ", click_favDown=" + click_favDown + "]";
	}
}
