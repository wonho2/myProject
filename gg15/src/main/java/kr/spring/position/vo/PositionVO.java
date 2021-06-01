package kr.spring.position.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class PositionVO
{
	private int pos_num;
	private String pos_type;
	private int mem_num;
	private String mem_nick;
	private String pos_title, pos_content;
	private byte[] pos_uploadfile;
	private String pos_filename;
	private Date pos_date;
	private int pos_view, pos_fav;
	
	//업로드 파일 처리
	public void setPos_upload(MultipartFile pos_upload)throws IOException
	{
		//MultipartFile -> byte[] 변환
		setPos_uploadfile(pos_upload.getBytes());
		//파일명 구하기
		setPos_filename(pos_upload.getOriginalFilename());
	}
	
	public int getPos_num() {
		return pos_num;
	}
	public void setPos_num(int pos_num) {
		this.pos_num = pos_num;
	}
	public String getPos_type() {
		return pos_type;
	}
	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
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
	public String getPos_title() {
		return pos_title;
	}
	public void setPos_title(String pos_title) {
		this.pos_title = pos_title;
	}
	public String getPos_content() {
		return pos_content;
	}
	public void setPos_content(String pos_content) {
		this.pos_content = pos_content;
	}
	
	public byte[] getPos_uploadfile() {
		return pos_uploadfile;
	}
	public void setPos_uploadfile(byte[] pos_uploadfile) {
		this.pos_uploadfile = pos_uploadfile;
	}
	public String getPos_filename() {
		return pos_filename;
	}
	public void setPos_filename(String pos_filename) {
		this.pos_filename = pos_filename;
	}
	public Date getPos_date() {
		return pos_date;
	}
	public void setPos_date(Date pos_date) {
		this.pos_date = pos_date;
	}
	public int getPos_view() {
		return pos_view;
	}
	public void setPos_view(int pos_view) {
		this.pos_view = pos_view;
	}
	public int getPos_fav() {
		return pos_fav;
	}

	public void setPos_fav(int pos_fav) {
		this.pos_fav = pos_fav;
	}
}
