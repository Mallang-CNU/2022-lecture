package view;

import java.util.Scanner;

/**
 * Created by ShinD on 2022-05-13.
 */
public class AppView {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean debugMode = false;


    public AppView(){}


    public static boolean debugMode() {
        return debugMode;
    }

    public static void setDebugMode(boolean newDebugMode) {
        AppView.debugMode = newDebugMode;
    }

    public static void outputDebugMessage(String aMessage){
        if (AppView.debugMode())
            System.out.print(aMessage);
    }

    public static void outputLineDebugMessage(String aMessage){
        if (AppView.debugMode())
            System.out.println(aMessage);
    }

    public static void output(String aMessage){
        System.out.print(aMessage);
    }

    public static void outputLine(String aMessage){
        System.out.println(aMessage);
    }

    public static String inputLine(){
        String line = AppView.scanner.nextLine().trim();
        while (line.equals("")){
            line = AppView.scanner.nextLine().trim();
        }
        return line;
    }

    public static char inputChar() {
        String line = AppView.scanner.nextLine().trim();
        while(line.equals("")){
            line = AppView.scanner.nextLine().trim();
        }
        return line.charAt(0);
    }

    public static void outputLine() {
        System.out.println();
    }
}

