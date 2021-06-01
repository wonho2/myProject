package kr.spring.position.etc;

public enum PositionType
{
	ALL("전체보기"), TOP("탑"), JUNGLE("정글"), MID("미드"), AD("원딜"), SUPPORT("서포터");
	
	// 생성자
	private PositionType(String value)
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
