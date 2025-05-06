import java.util.ArrayList;

public class FoodItemCollection {
    private final ArrayList<FoodItem> foodItems = new FoodDAO().getAll();


    public FoodItem findFood(String name){ return new FoodDAO().getByName(name); }

    public ArrayList<FoodItem> getFoodItems() { return foodItems; }

}
