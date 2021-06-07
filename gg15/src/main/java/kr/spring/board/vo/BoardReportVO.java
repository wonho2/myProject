package kr.spring.board.vo;
 
public class BoardReportVO {
	
	private int mem_num;//회원 번호
	private int boa_num;//게시글 번호
	private int bop_num;//신고 번호
	private String bop_content;//신고내용
	private String bop_date;//신고일
	private String mem_nick;//사용자 아이디
	
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
	public int getBop_num() {
		return bop_num;
	}
	public void setBop_num(int bop_num) {
		this.bop_num = bop_num;
	}
	public String getBop_content() {
		return bop_content;
	}
	public void setBop_content(String bop_content) {
		this.bop_content = bop_content;
	}
	public String getBop_date() {
		return bop_date;
	}
	public void setBop_date(String bop_date) {
		this.bop_date = bop_date;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
}
