public class Main {
    public static void main(String[] args) {
        // 1. Написать метод, который меняет два элемента массива местами (массив может быть любого
        //ссылочного типа);
        //2. Задача:
        //a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
        //b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу
        //фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
        //c. Для хранения фруктов внутри коробки можно использовать ArrayList;
        //d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта
        //и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
        //e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую
        //коробку с той, которую подадут в compare() в качестве параметра. true – если их массы
        //равны, false в противоположном случае. Можно сравнивать коробки с яблоками и
        //апельсинами;
        //f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
        //Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
        //Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
        //объекты, которые были в первой;
        //g. Не забываем про метод добавления фрукта в коробку.

        Object[] arrayTest = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        swapElements(arrayTest, 3, 5);
        for (int i = 0; i < arrayTest.length; i++) {
            System.out.print(arrayTest[i] + " ");
        }
    }

    // метод, который меняет два элемента массива местами
    public static void swapElements(Object[] array, int index1, int index2) {
                if ((0 <= index1) && (index1 < array.length) && (0 <= index2) && (index2 < array.length)) {
                    Object storage1 = array[index1];
                    Object storage2 = array[index2];
                    array[index1] = storage2;
                    array[index2] = storage1;
                } else {
                    if ((index1 < 0)||(index1 >= array.length)){
                        System.out.println("Элемент с индексом " + index1 + " отсутствует в массиве.");
                    } else {
                        System.out.println("Элемент с индексом " + index2 + " отсутствует в массиве.");
                    }
                }
    }
}
