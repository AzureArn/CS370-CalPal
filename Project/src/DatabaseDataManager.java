import java.util.ArrayList;
import java.util.Collections;

public class DatabaseDataManager {
    private ExerciseCollection exercises;
    private FoodItemCollection foodItems;

    DatabaseDataManager() { exercises.populate(); foodItems.populate(); }
    // public Item viewItem(String name) // commenting this out as we have left behind the Item -> Food -> Exercise hierarchy

    public FoodItem viewFood(String name){
        return foodItems.findFood(name);
    }

    public Exercise viewExercise(String name){
        return exercises.findExercise(name);
    }
}
