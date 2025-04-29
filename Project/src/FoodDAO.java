import java.util.*;
import java.io.FileReader;
import com.opencsv.*;

public class FoodDAO implements FoodDAOInterface{
    public FoodItem getByName(String name){
        // return new FoodItem();
        int cals = 0, grams = 0;
        boolean found = false;
        try {

            //gets file and filepath
            FileReader filereader = new FileReader(TextFileHandler.getFile("food.csv")); //absolutely SMACKING job ryan, this file handler is a godsend


            //read csv
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.skip(1); // skips header line

            String[] row;


            while((row = csvReader.readNext()) != null){
                if(row[0].equals(name)) { found = true; }
                if(found) {
                    cals = Integer.parseInt(row[1]); // column 2
                    grams = Integer.parseInt(row[2]); // column 3
                    break; // gets out of the loop
                }
            }

        } catch (Exception o) {System.out.println("Problem with foodDAO getByName"); o.printStackTrace(); System.exit(1); }
        if(!found) return null; // item is not found

        return new FoodItem(name, cals, grams); //found
    }

    public ArrayList<FoodItem> getAll() {
        ArrayList<FoodItem> foods = new ArrayList<>();
        String name;
        int cals, grams;
        try {
            FileReader filereader = new FileReader(TextFileHandler.getFile("food.csv"));
            CSVReader csvReader = new CSVReader(filereader);

            csvReader.skip(1); // skips header line

            String[] row;

            while((row = csvReader.readNext()) != null){
                name = row[0];
                cals = Integer.parseInt(row[1]);
                grams = Integer.parseInt(row[2]);

                foods.add(new FoodItem(name, cals, grams)); // adds food to arraylist
            }
        } catch (Exception o) { System.out.println("Problem with foodDAO GetAll."); o.printStackTrace(); System.exit(1); }

        return foods;

    }

}
