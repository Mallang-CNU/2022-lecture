package view;

import java.util.Scanner;

/**
 * Created by ShinD on 2022/03/17.
 */
public class AppView {

    private static Scanner sc = new Scanner(System.in) ;
    private AppView(){}

    public static void output(String message) {
        System.out.print(message);
    }
    public static void outputLine(String message) {
        System.out.println(message);
    }


    public static int inputMenuNumber() {
        outputLine("");
        output("? 수행하고자 하는 메뉴 번호를 선택하시오 (add: 1, remove: 2, search: 3, frequency: 4, exit: 9) : ");
        return sc.nextInt();
    }

    public static int inputCapacityOfCoinBag() {
        output("? 동전 가방의 크기, 즉 가방에 들어갈 동전의 최대 개수를 입력하시오: ");
        return sc.nextInt();
    }
    public static int inputCoinValue(){
        output("? 동전 값을 입력하시오: ");
        return sc.nextInt();
    }
}
