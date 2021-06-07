package kr.spring.manualtool.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ManualtoolVO {
	
	private int man_num;
	private int mem_num;
	private String man_champion;
	private String man_season;
	@NotEmpty
	@Size(max=20)
	private String man_title;
	@NotEmpty
	private String man_content;
	private byte[] man_uploadfile;
	private String man_filename;
	private Date man_date;
	private Date man_update;
	private int man_hit; 
	private int man_fav;
	private int man_comment;
	private String mem_nick;
	
	//업로드 파일 처리
		public void setMan_upload(MultipartFile man_upload)throws IOException{
			//MultipartFile -> byte[] 변환
			setMan_uploadfile(man_upload.getBytes());
			//파일명 구하기
			setMan_filename(man_upload.getOriginalFilename());
		}

	public int getMan_num() {
		return man_num;
	}
	public void setMan_num(int man_num) {
		this.man_num = man_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMan_champion() {
		return man_champion;
	}
	public void setMan_champion(String man_champion) {
		this.man_champion = man_champion;
	}
	public String getMan_season() {
		return man_season;
	}
	public void setMan_season(String man_season) {
		this.man_season = man_season;
	}
	public String getMan_title() {
		return man_title;
	}
	public void setMan_title(String man_title) {
		this.man_title = man_title;
	}
	public String getMan_content() {
		return man_content;
	}
	public void setMan_content(String man_content) {
		this.man_content = man_content;
	}
	public byte[] getMan_uploadfile() {
		return man_uploadfile;
	}
	public void setMan_uploadfile(byte[] man_uploadfile) {
		this.man_uploadfile = man_uploadfile;
	}
	public String getMan_filename() {
		return man_filename;
	}
	public void setMan_filename(String man_filename) {
		this.man_filename = man_filename;
	}
	public Date getMan_date() {
		return man_date;
	}
	public void setMan_date(Date man_date) {
		this.man_date = man_date;
	}
	public Date getMan_update() {
		return man_update;
	}
	public void setMan_update(Date man_update) {
		this.man_update = man_update;
	}
	public int getMan_hit() {
		return man_hit;
	}
	public void setMan_hit(int man_hit) {
		this.man_hit = man_hit;
	}
	public int getMan_fav() {
		return man_fav;
	}
	public void setMan_fav(int man_fav) {
		this.man_fav = man_fav;
	}
	public int getMan_comment() {
		return man_comment;
	}
	public void setMan_comment(int man_comment) {
		this.man_comment = man_comment;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	
}
