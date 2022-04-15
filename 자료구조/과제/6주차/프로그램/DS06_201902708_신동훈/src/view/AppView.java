package view;

/**
 * Created by ShinD on 2022/04/13.
 */
public class AppView {

    public static void outputLine(String aMessage) {
        System.out.println(aMessage);
    }
    public static void output(String aMessage) {
        System.out.print(aMessage);
    }
    public static void outputResults(int size, long durationForAdd, long durationForMax) {
        AppView.outputLine(
                "[크기:" + String.format("%5d", size) + "] " +
                        "삽입: " + String.format("%8d", durationForAdd) + ", " +
                        "최대값: " + String.format("%8d", durationForMax)
        );
    }


}
