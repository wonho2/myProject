package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class BoardVO {

	private int mem_num;//회원번호
	private int bao_num;//게시글번호
	private int bor_num;//댓글번호
	@NotEmpty
	private String boa_cate;//카테고리
	@NotEmpty
    private String boa_title = "";//게시글 제목
	@NotEmpty
    private String boa_content = "";//게시글 내용
	@NotEmpty
    private String bor_content = "";//댓글 내용
    private String boa_uploadfile="";//게시글 첨부파일
	private Date boa_date;//게시글 작성일
	private Date bor_date;//댓글 작성일
	private int boa_hot;//조회수
	
	
	
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getBao_num() {
		return bao_num;
	}
	public void setBao_num(int bao_num) {
		this.bao_num = bao_num;
	}
	public int getBor_num() {
		return bor_num;
	}
	public void setBor_num(int bor_num) {
		this.bor_num = bor_num;
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
	public String getBor_content() {
		return bor_content;
	}
	public void setBor_content(String bor_content) {
		this.bor_content = bor_content;
	}
	public String getBoa_uploadfile() {
		return boa_uploadfile;
	}
	public void setBoa_uploadfile(String boa_uploadfile) {
		this.boa_uploadfile = boa_uploadfile;
	}
	public Date getBoa_date() {
		return boa_date;
	}
	public void setBoa_date(Date boa_date) {
		this.boa_date = boa_date;
	}
	public Date getBor_date() {
		return bor_date;
	}
	public void setBor_date(Date bor_date) {
		this.bor_date = bor_date;
	}
	public int getBoa_hot() {
		return boa_hot;
	}
	public void setBoa_hot(int boa_hot) {
		this.boa_hot = boa_hot;
	}
	
	
}
