import java.awt.image.BufferedImage;

public class Exercise {
    private String name;
    private BufferedImage diagramImage;
    private int caloriesPerUnitExercise;
    boolean isCardio;


    // common constructor
    public Exercise(String name, BufferedImage image, int caloriesPerUnitExercise, boolean isCardio){
        this.name = name;
        this.diagramImage = image;
        this.caloriesPerUnitExercise = caloriesPerUnitExercise;
        this.isCardio = isCardio;
    }

    public String getName(){ return name; }

    public BufferedImage getImage(){ return diagramImage; }

    public int getCaloriesPerUnitExercise(){ return caloriesPerUnitExercise; }

    public boolean getIsCardio() { return isCardio; }

    public String display(){ return name + " " + diagramImage.toString() + " " + caloriesPerUnitExercise + Boolean.toString(this.isCardio); }

    @Override
    public String toString() { return name; }
}
