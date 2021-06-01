package kr.spring.party.vo;

import java.sql.Date;

public class PartyFavVO {
	private int fav_num;
	private Date fav_date;
	private int par_num;
	private int mem_num;
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public Date getFav_date() {
		return fav_date;
	}
	public void setFav_date(Date fav_date) {
		this.fav_date = fav_date;
	}
	
	public int getPar_num() {
		return par_num;
	}
	public void setPar_num(int par_num) {
		this.par_num = par_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
} 
