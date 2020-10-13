import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgeSql extends DaoFactory {
    private Connection connection = null;

    public PostgeSql(String driver) {
        super(driver);
    }

//    @Override
//    public void setURL(String host, int port) {
//        this.url = String.format("jdbc:postgresql://%s:%d;", host, port);
//    }

    @Override
    public void setURL(String host, String database, int port) {
        this.url = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void Connect(String login, String password) {
        super.Connect(login, password);
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException exception) {
            connection = null;
            exception.printStackTrace();
        }
    }
}

