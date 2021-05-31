package kr.spring.position.vo;

public class PositionFavVO
{
	private int pof_num; // 추천 번호
	private int pos_num; // 게시물 번호
	private int mem_num; // 회원 번호
	
	public int getPof_num() {
		return pof_num;
	}
	public void setPof_num(int pof_num) {
		this.pof_num = pof_num;
	}
	public int getPos_num() {
		return pos_num;
	}
	public void setPos_num(int pos_num) {
		this.pos_num = pos_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
