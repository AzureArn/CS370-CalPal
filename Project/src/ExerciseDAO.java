import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.FileReader;
import com.opencsv.*;

import javax.imageio.ImageIO;

public class ExerciseDAO implements ExerciseDAOInterface{
    public Exercise getByName(String name){
        int cals = 0;
        BufferedImage image = null;

        boolean found = false;

        try{
            //gets filepath & file
            FileReader filereader = new FileReader(TextFileHandler.getFile("exercises.csv"));

            //
            CSVReader csvreader = new CSVReader(filereader);
            csvreader.skip(1); // skips header line

            String[] row;

            while((row = csvreader.readNext()) != null){
                if(row[0].equals(name)){ found = true; }
                if(found){
                    image = ImageIO.read(TextFileHandler.getFile(row[1])); // still must be converted to a swing object by controller? or view?
                    cals = Integer.parseInt(row[2]);
                }
            }

            filereader.close();

        } catch(Exception o) { System.out.println("problem in exerciseDAO getbyname"); o.printStackTrace(); System.exit(1); }



        return new Exercise(name, image, cals);
    }

    public ArrayList<Exercise> getAll(){
        ArrayList<Exercise> exercises = new ArrayList<>();
        String name;
        int cals;
        BufferedImage image;

        try{
            FileReader filereader = new FileReader(TextFileHandler.getFile("exercises.csv"));

            CSVReader csvreader = new CSVReader(filereader);
            csvreader.skip(1); // skips header line

            String[] row;
            while((row = csvreader.readNext()) != null){
                name = row[0];
                image = ImageIO.read(TextFileHandler.getFile(row[1])); // still must be converted to a swing object by controller? or view?
                cals = Integer.parseInt(row[2]);

                exercises.add(new Exercise(name, image, cals));
            }
        } catch(Exception o) { System.out.println("problem in exerciseDAO getall"); o.printStackTrace(); System.exit(1); }



        return exercises;
    }
}
