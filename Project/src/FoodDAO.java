import java.util.ArrayList;

public class FoodDAO implements FoodDAOInterface{
    public FoodItem getByName(String name){
        return new FoodItem();
    }

    public ArrayList<FoodItem> getAll(){
        return new ArrayList<FoodItem>();
    }
}
