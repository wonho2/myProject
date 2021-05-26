package kr.spring.news.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class NewsVO {
	private int new_num;
	
	
	@NotEmpty
	private String mem_nick;
	private String new_title;
	private String new_content;
	private int new_hit;
	private Date new_date;
	private byte[] new_uploadfile;
	private int mem_num;
	private String id;
	private String new_comment;
	private String new_filename;
	
	//업로드 파일 처리
	public void setNew_upload(MultipartFile new_upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setNew_uploadfile(new_upload.getBytes());
		//파일명 구하기
		setNew_filename(new_upload.getOriginalFilename());
	}
	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getNew_filename() {
		return new_filename;
	}
	public void setNew_filename(String new_filename) {
		this.new_filename = new_filename;
	}
	public int getNew_num() {
		return new_num;
	}
	public void setNew_num(int new_num) {
		this.new_num = new_num;
	}
	public String getNew_title() {
		return new_title;
	}
	public void setNew_title(String new_title) {
		this.new_title = new_title;
	}
	public String getNew_content() {
		return new_content;
	}
	public void setNew_content(String new_content) {
		this.new_content = new_content;
	}
	public int getNew_hit() {
		return new_hit;
	}
	public void setNew_hit(int new_hit) {
		this.new_hit = new_hit;
	}
	public Date getNew_date() {
		return new_date;
	}
	public void setNew_date(Date new_date) {
		this.new_date = new_date;
	}
	public byte[] getNew_uploadfile() {
		return new_uploadfile;
	}
	public void setNew_uploadfile(byte[] new_uploadfile) {
		this.new_uploadfile = new_uploadfile;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNew_comment() {
		return new_comment;
	}
	public void setNew_comment(String new_comment) {
		this.new_comment = new_comment;
	}
	
}
