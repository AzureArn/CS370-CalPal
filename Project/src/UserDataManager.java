import java.time.LocalDate;
import java.util.ArrayList;

public class UserDataManager {
    private CalorieEntryCollection dataEntries;

    public UserDataManager(String userName){
        dataEntries = new CalorieEntryCollection(userName.strip().toLowerCase());
    }

    // gets the entry from the collection based on its date
    public CalorieDataEntry getEntry(String date){
        return dataEntries.findEntry(LocalDate.parse(date));
    }

    public CalorieDataEntry getCurrentEntry(){
        return dataEntries.getCurrentEntry();
    }

    // return the dates of all entries as an arraylist of strings
    public ArrayList<String> getAllDates(){
        ArrayList<String> dates = dataEntries.getAllDates();
        return dates;
    }

    // saves the calorie data for the current day
    public void saveCalorieData(int caloriesConsumed, int caloriesBurned){
        dataEntries.adjustCurrentEntry(caloriesConsumed, caloriesBurned);
    }
}
