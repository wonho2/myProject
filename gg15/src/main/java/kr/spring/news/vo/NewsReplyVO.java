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
	/*private int ner_rfav;*/
	
/*	public int getNer_rfav() {
		return ner_rfav;
	}
	public void setNer_rfav(int ner_rfav) {
		this.ner_rfav = ner_rfav;
	}*/
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
				+ mem_nick + ", ner_content=" + ner_content + ", ner_date=" + ner_date + "]";
	}

}
