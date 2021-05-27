package kr.spring.party.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author swift
 *
 */
public class PartyVO {
	private int par_num; 
	private int mem_num;
	private int par_type;
	@NotEmpty
	private String par_title;
	@NotEmpty
	private String par_content;
	private byte[] par_uploadfile;
	private String par_filename;
	private Date par_date;
	private int par_hit;
	private int par_fav;
	private int par_comment;
	private String mem_nick;
	
	//업로드 파일 처리
		public void setPar_upload(MultipartFile par_upload)throws IOException{
			//MultipartFile -> byte[] 변환
			setPar_uploadfile(par_upload.getBytes());
			//파일명 구하기
			setPar_filename(par_upload.getOriginalFilename());
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
	public int getPar_type() {
		return par_type;
	}
	public void setPar_type(int par_type) {
		this.par_type = par_type;
	}
	public String getPar_title() {
		return par_title;
	}
	public void setPar_title(String par_title) {
		this.par_title = par_title;
	}
	public String getPar_content() {
		return par_content;
	}
	public void setPar_content(String par_content) {
		this.par_content = par_content;
	}
	
	public byte[] getPar_uploadfile() {
		return par_uploadfile;
	}

	public void setPar_uploadfile(byte[] par_uploadfile) {
		this.par_uploadfile = par_uploadfile;
	}

	public String getPar_filename() {
		return par_filename;
	}

	public void setPar_filename(String par_filename) {
		this.par_filename = par_filename;
	}

	public Date getPar_date() {
		return par_date;
	}
	public void setPar_date(Date par_date) {
		this.par_date = par_date;
	}
	public int getPar_hit() {
		return par_hit;
	}
	public void setPar_hit(int par_hit) {
		this.par_hit = par_hit;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public int getPar_fav() {
		return par_fav;
	}

	public void setPar_fav(int par_fav) {
		this.par_fav = par_fav;
	}

	public int getPar_comment() {
		return par_comment;
	}

	public void setPar_comment(int par_comment) {
		this.par_comment = par_comment;
	}
	
	
}	