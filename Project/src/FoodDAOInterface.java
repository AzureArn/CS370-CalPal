import java.util.ArrayList;

public interface FoodDAOInterface extends DAOInterface<FoodItem> {
    public FoodItem getByName(String name);
    public ArrayList<FoodItem> getAll();
}
