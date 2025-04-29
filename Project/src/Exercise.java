import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Exercise {
    private String name;
    private BufferedImage diagramImage;
    private int caloriesPerUnitExercise;

    //constructing from filename
    public Exercise(String name, String filename, int caloriesPerUnitExercise){
        this.name = name;
        try{
            this.diagramImage = ImageIO.read(new File(filename));
        } catch (Exception o) { System.out.println("problem with image read"); o.printStackTrace(); System.exit(1); }
        this.caloriesPerUnitExercise = caloriesPerUnitExercise;
    }

    // common constructor
    public Exercise(String name, BufferedImage image, int caloriesPerUnitExercise){
        this.name = name;
        this.diagramImage = image;
        this.caloriesPerUnitExercise = caloriesPerUnitExercise;
    }

    @Override
    public String toString() {
        return name + " " + diagramImage.toString() + " " + caloriesPerUnitExercise; // change image obv.. just for demonstration right now
    }
}
