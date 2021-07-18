public class Main {
    public static void main(String[] args) {
        // 1. Написать метод, на вход которому подается двумерный строковый массив размером 4х4.
        // При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
        // 2. Далее метод должен пройтись по всем элементам массива,
        // преобразовать в int и просуммировать.
        // Если в каком-то элементе преобразование не удалось
        // (например, если в ячейке лежит символ или текст вместо числа),
        // надо бросить исключение MyArrayDataException с детализацией,
        // в какой ячейке неверные данные.
        // 3. В методе main() вызвать полученный метод,
        // обработать возможные исключения MySizeArrayException и MyArrayDataException
        // и вывести результат расчета.

        String[][] arrayValid = {{"5", "6", "2", "5"},
                                 {"4", "9", "7", "1"},
                                 {"9", "1", "3", "7"},
                                 {"2", "8", "3", "0"}};

        String[][] arrayWithWrongQuantity = {{"1", "5", "3", "7", "5", "8", "1"},
                                             {"1", "5", "3", "7", "8", "9", "1"},
                                             {"0", "4", "7", "2", "8", "6", "3"},
                                             {"7", "9", "4", "6", "1", "0", "4"},
                                             {"1", "7", "6", "4", "3", "8", "7"}};

        String[][] arrayWithWrongContent = {{"2", "4", "8", "one"},
                                            {"5", "hamster", "7", "3"},
                                            {"9", "7", "3", "2"},
                                            {"8", "1", "6", "0"}};

        try {
            System.out.println("Первый массив:");
            sumOfArrayElements(arrayValid);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }

        try {
            System.out.println("Второй массив:");
            sumOfArrayElements(arrayWithWrongQuantity);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }

        try {
            System.out.println("Третий массив:");
            sumOfArrayElements(arrayWithWrongContent);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e);
        }
    }

    public static void sumOfArrayElements(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length !=4) {
            throw new MyArraySizeException("Неверный размер массива: столбцов — " + array[0].length +
                           ", строк — " + array.length + ". Корректный размер массива — 4х4.");
        }
        int summ = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    int column = i + 1;
                    int row = j + 1;
                    throw new MyArrayDataException("Неверные данные в ячейке [" + column + "][" + row + "].");
                }
            }
        }
        System.out.println("Сумма всех элементов массива: " + summ + ".");
    }
}

