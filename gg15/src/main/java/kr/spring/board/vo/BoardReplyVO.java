package kr.spring.board.vo;

public class BoardReplyVO {
	
	private int mem_num;//회원번호
	private int boa_num;//게시글번호
	private int bor_num;//댓글번호
	private String bor_content;//댓글 내용
	private String bor_date;//댓글 작성일
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
	public int getBor_num() {
		return bor_num;
	}
	public void setBor_num(int bor_num) {
		this.bor_num = bor_num;
	}
	public String getBor_content() {
		return bor_content;
	}
	public void setBor_content(String bor_content) {
		this.bor_content = bor_content;
	}
	
	public String getBor_date() {
		return bor_date;
	}
	public void setBor_date(String bor_date) {
		this.bor_date = bor_date;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	@Override
	public String toString() {
		return "BoardReplyVO [mem_num=" + mem_num + ", boa_num=" + boa_num + ", bor_num=" + bor_num + ", bor_content="
				+ bor_content + ", bor_date=" + bor_date + ", mem_nick=" + mem_nick + "]";
	} 
}
