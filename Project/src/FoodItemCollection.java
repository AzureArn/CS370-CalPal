import java.util.ArrayList;

public class FoodItemCollection {
    private ArrayList<FoodItem> foodItems;

    public FoodItem findFood(String name){ return new FoodDAO().getByName(name); }

    public boolean populate(){ // new method to populate the collection
        foodItems = new FoodDAO().getAll(); // gets all object in file
        return foodItems.isEmpty();
    }
}
