import java.time.LocalDate;
import java.util.ArrayList;

public class CalorieEntryCollection {
    private String userName; // the user that this collection belongs to
    private ArrayList<CalorieDataEntry> entries; // the history of the user's entries


    // creates file for user with the given name if it doesn't exist,
    // return true if file created, false if not
    private static boolean createUserDataFile(String name){
        return TextFileHandler.createTextFile("user-" + name);
    }

    // constructor, userName argument value will be the name of the user that logged in
    public CalorieEntryCollection(String userName){
        entries = new ArrayList<CalorieDataEntry>();
        this.userName = userName;
        if(!CalorieEntryCollection.createUserDataFile(this.userName)){ // if the file already existed, check the file
            // put all lines of the file into an array
            String[] lines = TextFileHandler.getFileContents("user-" + this.userName).split("\\R");

            // while there are lines in the file, read the line and add the entry to the array
            for(String line : lines) {
                // yyyy-mm-dd::calConsumed||calBurned
                String date = line.substring(0, line.indexOf("::"));
                int calConsumed = Integer.parseInt(line.substring(line.indexOf("::") + 2, line.indexOf("||")));
                int calBurned = Integer.parseInt(line.substring(line.indexOf("||") + 2));
                entries.add(new CalorieDataEntry(calConsumed, calBurned, LocalDate.parse(date)));
            }

            // if there isn't an entry for the current day, create one
            if(! entries.getLast().getEntryDate().equals(LocalDate.now())){
                addEntry(new CalorieDataEntry());
            }
        }
        else{
            // if file didn't exist, add an entry for the current day
            addEntry(new CalorieDataEntry());
        }
    }

    // adds entry to the array list and file
    public void addEntry(CalorieDataEntry entry){
        String date = entry.getEntryDate().toString(); // format is yyyy-mm-dd
        int consumed = entry.getCaloriesConsumed();
        int burned = entry.getCaloriesBurned();

        // append line into file
        String line = date + "::" + consumed + "||" + burned + "\n";
        TextFileHandler.appendTextFile("user-" + this.userName, line);

        // place this new entry into the array
        entries.add(entry);
    }

    // return the entry corresponding to the given date, return null if not found
    public CalorieDataEntry findEntry(LocalDate date){
        for(CalorieDataEntry e : entries){
            if(e.getEntryDate().equals(date)){
                return e;
            }
        }
        return null; // if an entry for that date could not be found, return null
    }

    // adjust the most recent entry (the entry with the date that the user is currently accessing the app on)
    public void adjustCurrentEntry(int caloriesConsumed, int caloriesBurned){
        CalorieDataEntry entry = entries.getLast();
        entry.setCaloriesConsumed(caloriesConsumed);
        entry.setCaloriesBurned(caloriesBurned);

        // rewrite file
        String line = entry.getEntryDate().toString() + "::" + entry.getCaloriesConsumed() + "||" + entry.getCaloriesBurned() + "\n";
        TextFileHandler.changeLastLine("user-" + this.userName, line);
    }

    // return the dates of all entries as an arraylist of strings
    public ArrayList<String> getAllDates(){
        ArrayList<String> dates = new ArrayList<>();
        entries.forEach(
                (entry) -> dates.add(entry.getEntryDate().toString())
        );
        return dates;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CalorieDataEntry getCurrentEntry(){
        return entries.getLast();
    }
}
