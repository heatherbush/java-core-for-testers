import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // Реализовать сохранение данных в csv файл;
        // Реализовать загрузку данных из csv файла. Файл читается целиком.
        // Структура csv файла:
        // Строка заголовок с набором столбцов
        // Набор строк с целочисленными значениями
        //* Разделитель между столбцами - символ точка с запятой (;)
        //Пример:
        //Value 1;Value 2;Value 3
        //100;200;123
        //300,400,500

        AppData appData = new AppData();
        appData.load("file.csv");

        System.out.println(Arrays.toString(appData.getHeader()));
        System.out.println(Arrays.deepToString(appData.getData()));

        appData.save("file-2.csv");

    }
}
