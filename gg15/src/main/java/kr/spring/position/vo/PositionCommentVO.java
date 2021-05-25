package kr.spring.position.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class PositionCommentVO 
{
	private int poc_num;
	private int pos_num;
	private int mem_num;
	private String mem_nick;
	private String poc_content;
	private Date poc_date;
	private int poc_fav;
	
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
	public void setMem_num(int mem_num) {
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
		this.poc_content = poc_content;
	}
	public Date getPoc_date() {
		return poc_date;
	}
	public void setPoc_date(Date poc_date) {
		this.poc_date = poc_date;
	}
	public int getPoc_fav() {
		return poc_fav;
	}
	public void setPoc_fav(int poc_fav) {
		this.poc_fav = poc_fav;
	}
}
