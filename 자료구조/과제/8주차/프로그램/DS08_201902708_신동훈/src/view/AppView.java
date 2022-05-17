package view;

import java.util.*;

/**
 * Created by ShinD on 2022/04/30.
 */
public class AppView {


	private static Scanner scanner = new Scanner(System.in);


	public static void outputLine(String aMessage)
	{
		System.out.println(aMessage);
	}
	public static void output (String aMessage)
	{
		System.out.print(aMessage);
	}

	/**
	 * 문자 한 개를 입력받는다
	 * @return 입력받은 문자열
	 */
	public static char inputChar() {
		String line = AppView.scanner.nextLine().trim();
		while(line.equals(""))
		{
			line = AppView.scanner.nextLine().trim();
		}
		return line.charAt(0); 
	}
}
