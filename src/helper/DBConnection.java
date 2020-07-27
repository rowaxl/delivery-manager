package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBConsts.DBURL, DBConsts.USERNAME, DBConsts.PASSWORD);
    }
}
