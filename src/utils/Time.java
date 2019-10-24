package utils;

public class Time {

    public Time() {

    }

    public static void rest(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
