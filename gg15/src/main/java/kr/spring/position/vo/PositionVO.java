package kr.spring.position.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class PositionVO
{
	private int pos_num;
	private String pos_type;
	private int mem_num;
	@NotEmpty
	private String pos_title, pos_content;
	private byte[] pos_uploadfile;
	private Date pos_date;
	private int pos_view, pos_fav, pos_comment;  
	
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
	public void setPos_uploadfile(MultipartFile pos_uploadfile) throws IOException{
		this.pos_uploadfile = pos_uploadfile.getBytes();
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
	public int getPos_comment() {
		return pos_comment;
	}
	public void setPos_comment(int pos_comment) {
		this.pos_comment = pos_comment;
	}
	@Override
	public String toString() {
		return "PositionVO [pos_num=" + pos_num + ", pos_type=" + pos_type + ", mem_num=" + mem_num + ", pos_title="
				+ pos_title + ", pos_content=" + pos_content + ", pos_uploadfile=" + Arrays.toString(pos_uploadfile)
				+ ", pos_date=" + pos_date + ", pos_view=" + pos_view + ", pos_fav=" + pos_fav + ", pos_comment="
				+ pos_comment + "]";
	}
}
