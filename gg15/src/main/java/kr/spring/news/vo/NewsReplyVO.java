package kr.spring.news.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import kr.spring.util.DurationFromNow;

public class NewsReplyVO 
{
	private int ner_num;
	private int new_num;
	private int mem_num;
	private String mem_nick;
	private String ner_content;
	private String ner_date;
	private int ner_fav; // 댓글의 추천 수
	private int click_favUp; // 추천을 누른 회원 번호
	private int click_favDown; // 비추천을 누른 회원 번호
	
	
	public int getNer_fav() {
		return ner_fav;
	}
	public void setNer_fav(int ner_fav) {
		this.ner_fav = ner_fav;
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
	public int getNer_num() {
		return ner_num;
	}
	public void setNer_num(int new_num) {
		this.ner_num = new_num;
	}
	public int getNew_num() {
		return new_num;
	}
	public void setNew_num(int new_num) {
		this.new_num = new_num;
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
	public String getNer_content() {
		return ner_content;
	}
	public void setNer_content(String ner_content) {
		this.ner_content = ner_content;
	}
	
	public String getNer_date() {
		return ner_date;
	}
	public void setNer_date(String ner_date) {
		this.ner_date = DurationFromNow.getTimeDiffLabel(ner_date);
	}
	@Override
	public String toString() {
		return "NewsReplyVO [ner_num=" + ner_num + ", new_num=" + new_num + ", mem_num=" + mem_num + ", mem_nick="
				+ mem_nick + ", ner_content=" + ner_content + ", ner_date=" + ner_date + ", ner_fav=" + ner_fav
				+ ", click_favUp=" + click_favUp + ", click_favDown=" + click_favDown + "]";
	}
}
