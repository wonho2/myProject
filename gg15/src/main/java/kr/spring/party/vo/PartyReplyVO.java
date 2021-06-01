package kr.spring.party.vo;

public class PartyReplyVO {
	private int pop_num;
	private int par_num;
	private int mem_num;
	private String mem_nick;
	private String pop_content;
	private String pop_date;
	
	public int getPop_num() {
		return pop_num;
	}
	public void setPop_num(int pop_num) {
		this.pop_num = pop_num;
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
	
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getPop_content() {
		return pop_content;
	}
	public void setPop_content(String pop_content) {
		this.pop_content = pop_content;
	}
	public String getPop_date() {
		return pop_date;
	}
	public void setPop_date(String pop_date) {
		this.pop_date = pop_date;
	}
	@Override
	public String toString() {
		return "PartyReplyVO [pop_num=" + pop_num + ", par_num=" + par_num + ", mem_num=" + mem_num + ", mem_nick="
				+ mem_nick + ", pop_content=" + pop_content + ", pop_date=" + pop_date + "]";
	}
}  
