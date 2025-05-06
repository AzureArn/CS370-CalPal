import java.util.ArrayList;

public interface ExerciseDAOInterface extends DAOInterface<Exercise> {
    public Exercise getByName(String name);
    public ArrayList<Exercise> getAll();
}
