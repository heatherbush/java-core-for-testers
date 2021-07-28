import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {
    private HashMap<String, ArrayList<String>> phonebook = new HashMap<>();

    public void add(String name, String number) {
        ArrayList<String> phoneList = phonebook.getOrDefault(name, new ArrayList<>());
        phoneList.add(number);
        phonebook.put(name, phoneList);
    }

    public ArrayList<String> get(String name){
        if(phonebook.containsKey(name)) {
            System.out.println(name + ": " + phonebook.get(name));
        } else {
            System.out.println("Человека с таким именем в телефонной книге нет");
        }
        return null;
    }
}
