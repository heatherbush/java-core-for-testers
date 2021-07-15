public class Treadmill {
    private double length;

    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Treadmill(double length) {
        this.length = length;
    }

    public void runTheTrack(CanRun runner) {
        runner.iRun(this);
    }
}