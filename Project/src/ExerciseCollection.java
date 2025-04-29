import java.util.ArrayList;

public class ExerciseCollection {
    private ArrayList<Exercise> exercises = new ExerciseDAO().getAll();

    public Exercise findExercise(String name){
        return new ExerciseDAO().getByName(name);
    }

}
