public class Human implements CanRun, CanJump {
    private String name;
    private String kind;
    private double maxRun;
    private double maxJump;
    private double runningSpeed;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return this.kind;
    }

    public double getMaxRun() {
        return this.maxRun;
    }

    public void setMaxRun(double maxRun) {
        this.maxRun = maxRun;
    }

    public double getMaxJump() {
        return this.maxJump;
    }

    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }

    public double getRunningSpeed() {
        return this.runningSpeed;
    }

    public void setRunningSpeed(double runningSpeed) {
        this.runningSpeed = runningSpeed;
    }

    public Human(String name, double maxRun, double maxJump, double runningSpeed) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.runningSpeed = runningSpeed;
        this.kind = "Человек";
    }

    public void iJump(Wall wall) {
        System.out.println(this.kind + " " + this.name + " прыгает:");
        if (wall.getHeight() > this.maxJump) {
            System.out.println("Перепрыгнуть стену не удалось.\n");
        } else {
            System.out.println("Стена высотой " + wall.getHeight() + "м успешно перепрыгнута!\n");
        }

    }

    public void iRun(Treadmill treadmill) {
        System.out.println(this.kind + " " + this.name + " бежит:");
        if (treadmill.getLength() > this.maxRun) {
            System.out.println("Пробежать расстояние не удалось.\n");
        } else {
            double time = treadmill.getLength() / this.runningSpeed;
            System.out.println("Дистанция в " + treadmill.getLength() + "м успешно преодолена за " + time + " мин.\n");
        }

    }
}