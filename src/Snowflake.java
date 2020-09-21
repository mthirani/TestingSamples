import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by mayankthirani on 6/2/20.
 */
public class Snowflake {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            try {
                Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
            } catch (ClassNotFoundException ex) {
                System.err.println("Driver not found");
            }

            Properties properties = new Properties();
            properties.put("user", "Bigdatasnaplogic");
            properties.put("password", "Snaplogic2018");
            //properties.put("account", "");  // replace "" with your account name
            properties.put("db", "EXTREME_PERFORMANCE_DB");
            properties.put("schema", "PUBLIC");
            properties.put("warehouse", "EXTREME_PERFORMANCE");
            properties.put("role", "SYSADMIN");
            connection = DriverManager.getConnection(
                    "jdbc:snowflake://snaplogic.snowflakecomputing.com/?autocommit=false",
                    properties);
        } catch (SQLException e) {
            System.err.println("Connection Failed. User/Passwd Error?");
            return;
        }
        System.out.println("Connected");
        if (connection != null) {
            try {
                System.out.println("Connection to Snowflake successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * FROM TEST_ORDERS LIMIT 0");
                resultSet.next();
                int hello = resultSet.getInt(1);
                java.sql.DatabaseMetaData dbMetaData = connection.getMetaData();
//                resultSet = dbMetaData.getColumns(null, null, "\"SWAT1916_3\"",
//                        null);
                System.out.println("result fetched for first column first row: " + hello);

                String query = "INSERT INTO TEST_ORDERS(ID1, ID2) VALUES(?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setInt(1, 2);
                    ps.setInt(2, 21);
                    int result = ps.executeUpdate();
                    System.out.println("rows impacted: " + result);
                }

                //query = "SELECT * FROM TEST_ORDERS WHERE ? = ?";
                //query = "SELECT * FROM TEST_ORDERS WHERE ?";
                query = "SELECT * FROM TEST_ORDERS WHERE ID1 = ?";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    //ps.setString(1, "ID1 = 1");
                    //ps.setString(1, "ID1");
                    ps.setInt(1, 1);
                    ResultSet resultSet2 = ps.executeQuery();
                    resultSet2.next();
                    int r = resultSet2.getInt(1);
                    System.out.println("result fetched for search: " + r);
                }

            } catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
