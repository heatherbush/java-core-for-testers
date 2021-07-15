public class Main {

    public static void main(String[] args) {

        Human ralf = new Human("Ralf", 300, 1, 20);
        Cat bublik = new Cat("Бублик", 200, 1.8, 10);
        Robot bender = new Robot("Bender", 500, 3, 30);
        Human verter = new Human("Verter", 360, 1.5, 28);
        Cat knopa = new Cat("Кнопка", 230, 1.5, 14);
        Robot r2d2 = new Robot("R2D2", 600, 0.6, 40);

        Treadmill distance1 = new Treadmill(45);
        Wall wall1 = new Wall(1.6);
        Treadmill distance2 = new Treadmill(248);
        Wall wall2 = new Wall(0.8);
        Treadmill distance3 = new Treadmill(163);
        Wall wall3 = new Wall(2.3);
        Treadmill distance4 = new Treadmill(361);
        Wall wall4 = new Wall(4.9);

        Object[] competitors = {ralf, bublik, bender, verter, knopa, r2d2};
        Object[] obstacles = {distance1, wall1, distance2, wall2, distance3, wall3, distance4, wall4};

        for (Object competitor : competitors) {
            for (Object obstacle : obstacles) {
                if (obstacle instanceof Wall) {
                    // прыгаем
                    if (competitor instanceof Cat) {
                        ((Cat)competitor).iJump((Wall)obstacle);
                    } else if (competitor instanceof Human) {
                        ((Human)competitor).iJump((Wall)obstacle);
                    } else if (competitor instanceof Robot) {
                        ((Robot)competitor).iJump((Wall)obstacle);
                    }
                } else {
                    // бежим
                    if (competitor instanceof Cat) {
                        ((Cat)competitor).iRun((Treadmill)obstacle);
                    } else if (competitor instanceof Human) {
                        ((Human)competitor).iRun((Treadmill)obstacle);
                    } else if (competitor instanceof Robot) {
                        ((Robot)competitor).iRun((Treadmill)obstacle);
                    }
                }
            }
        }
    }
}

