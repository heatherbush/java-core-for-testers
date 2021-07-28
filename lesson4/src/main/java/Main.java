import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        // Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        // Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        // Посчитать, сколько раз встречается каждое слово.
        //Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
        // В этот телефонный справочник с помощью метода add() можно добавлять записи,
        // а с помощью метода get() искать номер телефона по фамилии.
        // Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
        // тогда при запросе такой фамилии должны выводиться все телефоны.
        // Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
        // взаимодействие с пользователем через консоль и т.д).
        // Консоль использовать только для вывода результатов проверки телефонного справочника.

        String[] words = {"Cat", "Giraffe", "Dog", "Dolphin", "Hare", "Wolf",
                        "Cat", "Hippopotamus", "Bear", "Giraffe", "Dolphin",
                        "Fox", "Owl", "Dog", "Giraffe", "Cat"};
        System.out.println(Arrays.asList(words));

        HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println(uniqueWords);

        HashMap<String, Integer> wordCount = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            var amount = 0;
            for (int j = 0; j < words.length; j++) {
                if(words[i].equals(words[j])) {
                    amount = amount + 1;
                }
            }
            wordCount.put(words[i], amount);
        }
        System.out.println(wordCount);


        Phonebook myPhonebook = new Phonebook();
        myPhonebook.add("Serov", "+79165557382");
        myPhonebook.add("Belov", "+79175577392");
        myPhonebook.add("Chernov", "+79185857342");
        myPhonebook.add("Serov", "+79164556382");
        myPhonebook.add("Chernov", "+79205856342");

        myPhonebook.get("Serov");
    }
}
