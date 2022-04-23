package view;

import java.util.Scanner;


/**
 * Created by ShinD on 2022/04/22.
 */
public class AppView {

    //== 비공개 상수/변수들 ==//
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructor
     * AppView 는 객체를 사용하여 만들 것이 아니기 때문에
     * 생성자를 private 으로 정의하여 객체를 생성하여 사용하는 것을 방지한다.
     */
    private AppView() {}


    /**
     * 줄바꿈 없이 메세지를 출력한다.
     * @param message 출력할 메세지
     */
    public static void output(String message) {
        System.out.print(message);
    }

    /**
     * 메세지를 출력후 줄을 바꾼다.
     * @param message 출력할 메세지
     */
    public static void outputLine(String message) {
        System.out.println(message);
    }

    /**
     * 학급 학생 수를 출력한다.
     * @param aNumberOfStudents 학급 학생 수
     */
    public static void outputNumberOfStudents (int aNumberOfStudents) {
        AppView.outputLine("학급 학생 수:" + aNumberOfStudents);
    }

    /**
     * 학급 내 최고 점수를 출력한다.
     * @param aScore 학급 내 최고 점수
     */
    public static void outputHighestScore (int aScore) {
        AppView.outputLine("학급 최고 점수: " + aScore);
    }

    /**
     * 학급 내 최저 점수를 출력한다.
     * @param aScore 학급 내 최고저 점수
     */
    public static void outputLowestScore (int aScore) {
        AppView.outputLine("학급 최저 점수: " + aScore);
    }

    /**
     * 학급 평균 점수를 출력한다
     * @param anAverageScore 학급 평균 점수
     */
    public static void outputAverageScore (double anAverageScore) {
        AppView.outputLine(String.format("학급 평균: %.1f", anAverageScore));
    }

    /**
     * 평균 이상인 학생 수를 출력한다.
     * @param aNumberOfStudents 평균 이상인 학생 수
     */
    public static void outputNumberOfStudentsAboveAverage (int aNumberOfStudents) {
        AppView.outputLine("평균 이상인 학생 수: " + aNumberOfStudents);
    }

    /**
     * 각 학점에 대한 학생 수 출력
     * @param aGrade
     * @param aNumberOfStudents
     */
    public static void outputNumberOfStudentsForGrade (char aGrade, int aNumberOfStudents) {
        AppView.outputLine(aGrade + " 학점의 학생 수는 " + aNumberOfStudents + " 입니다.");
    }

    /**
     * 학생들의 점수 출력
     * @param aScore
     */
    public static void outputScore (int aScore) {
        AppView.outputLine("점수:   " + aScore);
    }


    /**
     * 정수를 입력받는다.
     * @return 입력받은 정수
     * @throws NumberFormatException 정수 평테가 아닌 다른 형식으로 입력받을시 발생
     */
    public static int inputInt () throws NumberFormatException  {
        return Integer.parseInt(AppView.scanner.nextLine());
    }

    /**
     * 점수를 입력받는다.
     * @return 입력받은 점수
     */
    public static int inputScore() {
        while(true) {
            try {
                AppView.output("- 점수를 입력하시오 (0..100): ");
                int score = AppView.inputInt();
                return score;
            } catch ( NumberFormatException e) {
                AppView.outputLine("(오류) 정수가 입력되지 않았습니다.");
            }
        }

    }

    /**
     * 계속해서 점수를 입력받을지에 대한 여부를 반환한다.
     * @return 계속 입력받으면 true
     */
    public static boolean doesContinueToInputStudent() {
        AppView.output("성적을 입력하려면 'Y' 또는 'y'를, 종료하려면 다른 아무 키나 치시오: ");
        String line = null;
        do {
            line = AppView.scanner.nextLine();
        }while(line.equals("") );

        char answer = line.charAt(0);
        return ( (answer == 'Y') || (answer == 'y') );
    }


}
