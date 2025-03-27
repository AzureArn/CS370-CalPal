import java.time.LocalDate;

public class UserDataManager {
    private CalorieEntryCollection dataEntries;

    public UserDataManager(String userName){
        dataEntries = new CalorieEntryCollection(userName);
    }

    public void viewEntry(LocalDate date){
        CalorieDataEntry entry = dataEntries.findEntry(date);
        if(entry == null){
            System.out.println("Entry could not be found");
            return;
        }
        System.out.println("Date: " + entry.getEntryDate().toString());
        System.out.println("Calories Consumed: " + entry.getCaloriesConsumed());
        System.out.println("Calories Burned: " + entry.getCaloriesBurned());
        System.out.println("Net calories gained: " + entry.getNetCalories());
    }

    public void saveCalorieData(int caloriesConsumed, int caloriesBurned){
        dataEntries.adjustCurrentEntry(caloriesConsumed, caloriesBurned);
    }
}
