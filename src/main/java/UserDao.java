import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements Dao<User> {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAll() {
        String sql = String.valueOf(Query.SELECT.QUERY);

        List<User> list = new ArrayList<User>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                list.add(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return list;
    }

    @Override
    public List<User> getById(int id) {
        String sql = "SELECT * FROM user_ WHERE user_id = ?;";

        List<User> list = new ArrayList<User>();

        User user = new User();

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            list.add(user);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean insert(User obj) {
        boolean resultInsert = false;

        try (PreparedStatement statement = connection.prepareStatement(Query.INSERT.QUERY)) {
            statement.setString(1, obj.getFirstName());
            statement.setString(2, obj.getLastName());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getPhoneNumber());
            resultInsert = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultInsert;
    }

    @Override
    public boolean update(User obj, int id) {
        boolean resultUpdate = false;

        obj.setId(id);

        try (PreparedStatement statement = connection.prepareStatement(Query.UPDATE.QUERY)) {
            statement.setInt(1, obj.getId());
            resultUpdate = statement.executeQuery().next();
        } catch (SQLException e) {
            if (resultUpdate == false) {
                e.printStackTrace();
            } else System.out.println("Update!");
        }

        return resultUpdate;
    }

    @Override
    public boolean delete(User obj, int id) {
        boolean resultDelete = false;

        obj.setId(id);

        try (PreparedStatement statement = connection.prepareStatement(Query.DELETE.QUERY)) {
            statement.setInt(1, obj.getId());
            resultDelete = statement.executeQuery().next();
        } catch (SQLException e) {
            if (resultDelete == false) {
                e.printStackTrace();
            } else System.out.println("Delete!");
        }

        return resultDelete;
    }

    @Override
    public List<User> pagination(int limitPerPage, int page) {
        int p = page;
        int l = limitPerPage;
        int offsetValue = calculateOffset(p, l);

        String sql = "select * from user_ limit (?) offset (?)";

        List<User> userList = new ArrayList<User>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, l);
            statement.setInt(2, offsetValue);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                userList.add(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    private int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

    @Override
    public List<User> filter(String... field) {

        return null;
    }
}
