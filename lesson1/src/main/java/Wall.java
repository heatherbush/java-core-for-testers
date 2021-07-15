public class Wall {
    private double height;

    public double getHeight() {
        return this.height;
    }

    public void setLength(double height) {
        this.height = height;
    }

    public Wall(double height) {
        this.height = height;
    }

    public void jumpOverTheWall(CanJump jumper) {
        jumper.iJump(this);
    }
}