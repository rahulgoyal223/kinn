package utils;

import java.util.Random;

public class NumberUtil {

	public static final int MAX_INT_NUMBER = 100000;
	
	public static Integer getRandomNumber() {
		
		Random random=new Random();
		return random.nextInt(MAX_INT_NUMBER);
	}
	
}
