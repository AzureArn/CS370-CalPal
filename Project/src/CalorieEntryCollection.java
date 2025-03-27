import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class CalorieEntryCollection {
    private String userName; // the user that this collection belongs to
    private ArrayList<CalorieDataEntry> entries; // the history of the user's entries

    // only called by other functions to find the folder or file
    // returns the File object containing the folder or a File object with an empty string
    private static File findFile(String fileName){
        // get path to Project directory
        String projectPath = "";
        try {
            Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir")));
            paths = paths.filter(str ->  str.toString().endsWith(fileName));
            Optional<Path> p = paths.findAny();
            if (p.isPresent()) {
                projectPath = p.get().toString();
            }
            paths.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return new File(projectPath);
    }

    // only called by the other functions, creates file if it doesn't exist,
    // return true if file created, false if not
    // name argument is used to adjust the file name so it differs between users
    private static boolean createUserDataFile(String name){
        File folder = CalorieEntryCollection.findFile("Project");
        if(folder.getName().isEmpty()){ // if the folder could not be found
            System.out.println("Couldn't find the folder");
            System.exit(2);
        }
        // new file placed in the chosen folder
        File file = new File(folder,"user-" + name + ".txt");
        boolean fileCreated = false;
        try{
            fileCreated = file.createNewFile();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(3);
        }

        return fileCreated;
    }

    // constructor, userName argument value will be the name of the user that logged in
    public CalorieEntryCollection(String userName){
        entries = new ArrayList<CalorieDataEntry>();
        this.userName = userName;
        if(!CalorieEntryCollection.createUserDataFile(this.userName)){ // if the file already existed, check the file
            try{
                // finds the file in the Project folder
                Scanner reader = new Scanner(new FileReader(new File(CalorieEntryCollection.findFile("Project"), "user-" + userName + ".txt")));
                // while there are lines in the file, read the line and add the users to the array
                while(reader.hasNextLine()){
                    String line = reader.nextLine();
                    // yyyy-mm-dd::calConsumed||calBurned
                    String date = line.substring(0, line.indexOf("::"));
                    int calConsumed = Integer.parseInt(line.substring(line.indexOf("::") + 2, line.indexOf("||")));
                    int calBurned = Integer.parseInt(line.substring(line.indexOf("||") + 2));
                    entries.add(new CalorieDataEntry(calConsumed, calBurned, LocalDate.parse(date)));
                }

                // if the last entry doesn't have today's date, add another entry for today
                if(!(entries.getLast().getEntryDate().equals(LocalDate.now()))){
                    addEntry(new CalorieDataEntry());
                }
                reader.close();

            } catch(Exception fnfe){
                fnfe.printStackTrace();
                System.exit(4);
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
        // write into file
        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(CalorieEntryCollection.findFile("Project"), "user-" + this.userName + ".txt"),true));
            // data in the file are stored in this format: date::consumed||burned
            bWriter.write(date + "::" + consumed + "||" + burned + "\n");
            bWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(5);
        }

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
        // rewrite file using the entries array
        try{
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(new File(CalorieEntryCollection.findFile("Project"), "user-" + this.userName + ".txt"),false));
            // data in the file are stored in this format: date::consumed||burned
            for(CalorieDataEntry e : entries){
                bWriter.write(e.getEntryDate().toString() + "::" + e.getCaloriesConsumed() + "||" + e.getCaloriesBurned() + "\n");
            }
            bWriter.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(5);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
