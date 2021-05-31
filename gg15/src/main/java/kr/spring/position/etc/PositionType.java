package kr.spring.position.etc;

public enum PositionType
{
	ALL(0), TOP(1), JUNGLE(2), MID(3), AD(4), SUPPORT(5);
	
	// 생성자
	private PositionType(int value)
	{ 
		this.value = value;
	}
	
	// 상수값
	private final int value;
	public final int getValue()
	{ 
		return value;
	}
}
