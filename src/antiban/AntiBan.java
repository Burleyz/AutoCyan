package antiban;

public class AntiBan {

    public AntiBan() {

    }

    public static int randomValue(double min, double max) {
        double x = (Math.random()*((max-min) + 1)) + min;
        return (int)x;
    }
}
