import java.util.ArrayList;

public class ExerciseCollection {
    private ArrayList<Exercise> exercises;

    public Exercise findExercise(String name){
        return new ExerciseDAO().getByName(name);
    }

    // update graphics
    public boolean populate(){
        exercises = new ExerciseDAO().getAll();
        return exercises.isEmpty();
    }
}
