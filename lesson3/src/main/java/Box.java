import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> boxOfFruit = new ArrayList();

    public Box(ArrayList<T> boxOfFruit) {
        this.boxOfFruit = boxOfFruit;
    }

    public Box(T... fruits) {
        boxOfFruit = new ArrayList<>(Arrays.asList(fruits));
    }

    public ArrayList<T> getBoxOfFruit() {
        return boxOfFruit;
    }

    public void setBoxOfFruit(ArrayList<T> boxOfFruit) {
        this.boxOfFruit = boxOfFruit;
    }

    //метод getWeight()
    public float getWeight() {
        return boxOfFruit.size() * boxOfFruit.get(0).getWeight();
    }

    //метод compare()
    public boolean compare(Box<?> boxToCompare) {
        return Math.abs(getWeight() - boxToCompare.getWeight()) < 0.0001;
    }

    //метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    public void transferFruit(Box<T> newBox){
        newBox.getBoxOfFruit().addAll(boxOfFruit);
        boxOfFruit.clear();
    }

    //метод добавления фрукта в коробку.
    public void addFruit(T fruit) {
        boxOfFruit.add(fruit);
    }
}
