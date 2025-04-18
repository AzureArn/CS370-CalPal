import java.util.ArrayList;

public interface DAOInterface<T> {
    public T getByName(String name);
    public ArrayList<T> getAll();
}

