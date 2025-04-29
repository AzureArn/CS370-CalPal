import java.util.ArrayList;

public class FoodItemCollection {
    private ArrayList<FoodItem> foodItems = new FoodDAO().getAll();

    public FoodItem findFood(String name){ return new FoodDAO().getByName(name); }

}
