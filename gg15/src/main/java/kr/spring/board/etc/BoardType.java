package kr.spring.board.etc;

public enum BoardType
{
	ALL("전체보기"), gaming_machine("게이밍 기기"),game_talk("게임 이야기"),Discode("디스코드 홍보"),Tier("티어별 게시판"),Champion("챔피언별 게시판"),Humor("유머게시판"),img_video("사진/비디오"),art("팬아트");
	
	// 생성자
	private BoardType(String value)
	{ 
		this.value = value;
	}
	
	// 상수값
	private final String value;
	public final String getValue()
	{ 
		return value;
	}
}
