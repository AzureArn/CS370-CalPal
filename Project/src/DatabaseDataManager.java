import java.util.ArrayList;

public class DatabaseDataManager {
    private final ExerciseCollection exercises = new ExerciseCollection();
    private final FoodItemCollection foodItems = new FoodItemCollection();


    public FoodItem viewFood(String name){
        return foodItems.findFood(name);
    }

    public Exercise viewExercise(String name){
        return exercises.findExercise(name);
    }

    public ArrayList<Exercise> getExercises(){ return exercises.getExercises(); }

    public ArrayList<FoodItem> getFoods(){ return foodItems.getFoodItems(); }
}
