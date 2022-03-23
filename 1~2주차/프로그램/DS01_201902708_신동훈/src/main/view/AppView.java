package main.view;

import java.util.Scanner;

/**
 * Created by ShinD on 2022-03-03.
 */
public class AppView {

    private static Scanner sc = new Scanner(System.in) ;
    private AppView(){}

    public static int inputOrder(){
        System.out.print("? 마방진 차수를 입력하시오 (음수를 입력하면 종료합니다): ");

        return sc.nextInt();
    }

    public static void output(String message) {
        System.out.print(message);
    }
    public static void outputLine(String message) {
        System.out.println(message);
    }
    public static void outputTitleWithOrder(int order) {
        System.out.println("! Magic Square Board: Order " + order);
    }

    public static void outputRowNumber(int number) {
        System.out.printf("[%3d] ", number);
        
    }

    public static void outputCellValue(int value) {
        System.out.printf("  %3d ", value);
    }


}
