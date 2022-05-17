package model;

import java.util.Scanner;
/**
 * Created by ShinD on 2022/05/03.
 */
public class AppView {

	private static Scanner scanner = new Scanner(System.in);
	private static boolean debugMode = false;
	

	public AppView() {}


	public static boolean debugMode()
	{
		return debugMode;
	}
	public static void setDebugMode(boolean newDebugMode)
	{
		debugMode = newDebugMode;
	}

	/**
	 * 디버그 메세지를 출력한다. (줄바꿈 없음)
	 * @param aMessage 출력할 메세지
	 */
	public static void outputDebugMessage(String aMessage) {
		if(AppView.debugMode()) {
			System.out.print(aMessage);
		}
	}

	/**
	 * 디버그 메세지를 출력한다. (줄바꿈 있음)
	 * @param aMessage 출력할 메세지
	 */
	public static void outputLineDebugMessage(String aMessage) {
		if(AppView.debugMode()) {
			System.out.println(aMessage);
		}
	}

	public static void output(String aMessage)
	{
		System.out.print(aMessage);
	}
	public static void outputLine(String aMessage)
	{
		System.out.println(aMessage);
	}

	/**
	 * 한 줄만 입력받는다.
	 * 빈 줄일 경우 다시 입력받는다.
	 * @return 입력이 종료된 후
	 */
	public static String inputLine()
	{
		String line = AppView.scanner.nextLine().trim();
		while(line.equals("")) {
			line = AppView.scanner.nextLine().trim();
		}
		return line;
	}
}
	
	
