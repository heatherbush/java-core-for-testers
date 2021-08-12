import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя города: ");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получения погоды на сегодня; \n" +
                    "Введите 5 для прогноза на 5 дней; \nДля выхода введите 0:");
            String command = scanner.nextLine();

            System.out.println("Введите 2 для вывода данных из базы; \n" +
                    "Введите 4, если не хотите выводить данные из базы; \nДля выхода введите 0:");
            String base = scanner.nextLine();

            //TODO* Сделать метод валидации пользовательского ввода

            if (command.equals("0")) break;

            try {
                controller.getWeather(command, city, base);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
