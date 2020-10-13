import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DaoFactory {
    protected String driver;
    protected String url;
//    protected Connection connection;

    protected Properties properties = null;

    public DaoFactory(String driver) {
        this.driver = driver;
    }

    protected void RegisterDriverManager() {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public abstract void setURL(String host, String database, int port);

//    public abstract void setURL(String host, int port);

    public abstract Connection getConnection();

    public void Connect(String login, String password) {
        RegisterDriverManager();

        properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", login);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
    }

    public void Disconnect(Connection connection) {
        try {
            connection.close();
            connection = null;
            System.out.println("Disconnect");
        } catch (SQLException e) {
        }
    }
}
