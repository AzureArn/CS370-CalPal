import java.time.LocalDate;
// used to store the date, net calorie change, gain, and loss
// there should only be one entry per day
public class CalorieDataEntry {
    private LocalDate entryDate;
    private int netCalories;
    private int caloriesConsumed;
    private int caloriesBurned;

    // default constructor
    public CalorieDataEntry(){
        entryDate = LocalDate.now(); // get current day
        netCalories = 0;
        caloriesConsumed = 0;
        caloriesBurned = 0;
    }

    // argument constructor, arguments should not be negative
    public CalorieDataEntry(int consumed, int burned){
        entryDate = LocalDate.now(); // get current day
        setCaloriesConsumed(consumed);
        setCaloriesBurned(burned);
        netCalories = caloriesConsumed - caloriesBurned;
    }

    // setter for caloriesBurned, returns false if negative value and sets to 0, also sets net value
    public boolean setCaloriesBurned(int caloriesBurned) {
        if(caloriesBurned < 0){
            this.caloriesBurned = 0;
            netCalories = caloriesConsumed - this.caloriesBurned;
            return false;
        }
        this.caloriesBurned = caloriesBurned;
        netCalories = caloriesConsumed - this.caloriesBurned;
        return true;
    }

    // setter for caloriesConsumed, returns false if negative value and sets to 0, also sets net value
    public boolean setCaloriesConsumed(int caloriesConsumed){
        if(caloriesConsumed < 0){
            this.caloriesConsumed = 0;
            netCalories = this.caloriesConsumed - caloriesBurned;
            return false;
        }
        this.caloriesConsumed = caloriesConsumed;
        netCalories = this.caloriesConsumed - caloriesBurned;
        return true;
    }

    // getter for caloriesBurned
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    // getter for caloriesConsumed
    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }
}
