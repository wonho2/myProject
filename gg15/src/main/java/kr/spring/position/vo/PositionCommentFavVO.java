package kr.spring.position.vo;

public class PositionCommentFavVO 
{
	private int pocf_num; // 댓글 추천 번호
	private int poc_num; // 댓글 번호
	private int mem_num; // 추천을 누른 회원 번호
	
	public int getPocf_num() {
		return pocf_num;
	}
	public void setPocf_num(int pocf_num) {
		this.pocf_num = pocf_num;
	}
	public int getPoc_num() {
		return poc_num;
	}
	public void setPoc_num(int poc_num) {
		this.poc_num = poc_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
