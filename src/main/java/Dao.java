import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll() ;
    List<T> getById(int id);
    boolean insert(T obj);
    boolean update(T obj, int key);
    boolean delete(T obj, int key);

    List<T>pagination(int limitPerPage, int page);
    List<T>filter(String...field);
}
