package kr.spring.member.vo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberVO {
	private int mem_num;
	@Pattern(regexp="^[A-Za-z0-9+]{4,12}$")
	private String mem_id;
	private int mem_auth;
	@NotEmpty
	private String mem_name;
	@Size(min=4,max=12)
	private String mem_pw;
	@NotEmpty
	private String mem_nick;
	@NotEmpty
	private String mem_phone;
	@Email
	@NotEmpty
	private String mem_email;
	private int mem_point;
	private Date reg_date;
	private Date modify_date;

	//==========비밀번호 일치 여부 체크===========//
	public boolean isCheckedPassword(String userPasswd) {
		if(mem_auth > 1 && mem_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", mem_name="
				+ mem_name + ", mem_pw=" + mem_pw + ", mem_nick=" + mem_nick + ", mem_phone=" + mem_phone
				+ ", mem_email=" + mem_email + ", reg_date=" + reg_date + ", modify_date=" + modify_date + "]";
	}

	
	
}