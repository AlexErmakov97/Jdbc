import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class RunJdbc {
    public RunJdbc() {
        DaoFactory postgresql = new PostgeSql("org.postgresql.Driver");

        postgresql.setURL("localhost", "dao", 5432);

        postgresql.Connect("postgres", "admin");

        Util.checkConnection(postgresql.getConnection());
        Statement statement = null;

        try {
            statement = postgresql.getConnection().createStatement();
            statement.executeQuery(String.valueOf(Query.CREATE_TABLE.QUERY));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        Util.insertDataToUser(postgresql.getConnection(),statement,
                "C:/Users/User/IdeaProjects/dao/src/main/resources/user_.sql");

        Dao userDao = new UserDao(postgresql.getConnection());

        Util.viewData(userDao.getAll());

        System.out.println();

        Util.viewData(userDao.getById(32));

        User user = new User();

        userDao.delete(user, 7);

        userDao.update(user, 10);

        User newUser = new User("Alexander","Ermakov","karamba@mail.ru","88005553535");
        userDao.insert(newUser);

        Util.viewData(userDao.pagination(10,1));


        postgresql.Disconnect(postgresql.getConnection());
    }
}

