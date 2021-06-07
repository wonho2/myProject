package kr.spring.board.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
 
	private int user_auth;//회원 등급
	private int mem_num;//회원번호
	private int boa_num;//게시글번호
	private String boa_cate;//카테고리
	@NotEmpty
	@Size(max=10)
	private String boa_title;//게시글 제목
	@NotEmpty
	private String boa_content;//게시글 내용
	private Date boa_date;//게시글 작성일
	private byte[] boa_uploadfile;//업로드 파일
	private String boa_filename;//업로드 파일 이름
	private int boa_mode; //회원전용 글
	private int boa_status; // 차단상태
	private int boa_hit;	//조회수
	private int bof_num;	//좋아요
	private int boa_report; //신고
	private String mem_nick;
	 
	//업로드 파일 처리
	public void setBoa_upload(MultipartFile boa_upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setBoa_uploadfile(boa_upload.getBytes());
		//파일명 구하기
		setBoa_filename(boa_upload.getOriginalFilename());
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int mem_auth) {
		this.user_auth = mem_auth;
	}



	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public int getBoa_num() {
		return boa_num;
	}

	public void setBoa_num(int boa_num) {
		this.boa_num = boa_num;
	}

	public String getBoa_cate() {
		return boa_cate;
	}
	public void setBoa_cate(String boa_cate) {
		this.boa_cate = boa_cate;
	}
	public String getBoa_title() {
		return boa_title;
	}
	public void setBoa_title(String boa_title) {
		this.boa_title = boa_title;
	}
	public String getBoa_content() {
		return boa_content;
	}
	public void setBoa_content(String boa_content) {
		this.boa_content = boa_content;
	}
	public Date getBoa_date() {
		return boa_date;
	}
	public void setBoa_date(Date boa_date) {
		this.boa_date = boa_date;
	}
	public byte[] getBoa_uploadfile() {
		return boa_uploadfile;
	}
	public void setBoa_uploadfile(byte[] boa_uploadfile) {
		this.boa_uploadfile = boa_uploadfile;
	}

	public String getBoa_filename() {
		return boa_filename;
	}

	public void setBoa_filename(String boa_filename) {
		this.boa_filename = boa_filename;
	}

	public int getBoa_mode() {
		return boa_mode;
	}

	public void setBoa_mode(int boa_mode) {
		this.boa_mode = boa_mode;
	}
	
	public int getBoa_status() {
		return boa_status;
	}

	public void setBoa_status(int boa_status) {
		this.boa_status = boa_status;
	}

	public int getBoa_hit() {
		return boa_hit;
	}

	public void setBoa_hit(int boa_hit) {
		this.boa_hit = boa_hit;
	}
	
	public int getBof_num() {
		return bof_num;
	}

	public void setBof_num(int bof_num) {
		this.bof_num = bof_num;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	
	public int getBoa_report() {
		return boa_report;
	}

	public void setBoa_report(int boa_report) {
		this.boa_report = boa_report;
	}

	@Override
	public String toString() {
		return "BoardVO [mem_num=" + mem_num + ", user_auth" + user_auth +", bao_num=" + boa_num + ", boa_cate=" + boa_cate + ", boa_title="
				+ boa_title + ", boa_content=" + boa_content + ", boa_date=" + boa_date + ", boa_filename="
				+ boa_filename + "]";
	}
}
