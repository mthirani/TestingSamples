package SnowflakeJDBCCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by mayankthirani on 3/8/19.
 */
public class JDBCCommands {
    public static void main(String []args) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "Danila Mikhaylutsa");
        connectionProps.put("password", "snaplogic123");
        connectionProps.put("db", "SNAPQADB");
        connectionProps.put("warehouse", "DEMO_WH");
        connectionProps.put("schema", "PRASANNA");
        connectionProps.put("ssl", "on");
        String test = String.format("%s%s", "Mayank", "Thirani");
        System.out.println(test);
//        String jdbcURL = "jdbc:snowflake://snaplogic.snowflakecomputing" +
//                ".com:443/?ssl=on&user=Danila " +
//                "Mikhaylutsa&password=snaplogic123&db=SNAPQADB&warehouse=DEMO_WH&schema=PRASANNA";
        try {
//            Connection conn = DriverManager.getConnection(jdbcURL);
            Connection conn = DriverManager.getConnection("jdbc:snowflake://snaplogic" +
                    ".snowflakecomputing.com:443", connectionProps);
            System.out.println("Is connection closed :: " + conn.isClosed());
            if (conn != null) {
                System.out.println("Connection is successfull");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM SWAT1861");
                System.out.println("ID, value");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2));
                }

                //Execute PUT command
//                statement.executeQuery("PUT " +
//                        "'file:////Users/mayankthirani/Downloads/" +
//                        "snowflakebuklload_data_f1c6dbb9-d326-4a3d-b591-cec3c51d3cee.tmp' '@~/INPUTVIEW/042356a6-5c89-4e6a-87fc-80e1b880bdd8/'");
                statement.executeQuery("PUT " +
                        "'file:////Users/mayankthirani/Desktop/SWAT0x002F1861/" +
                        "snowflakebuklload_data.tmp' '@~/INPUTVIEW/042356a6-5c89-4e6a-87fc-80e1b880bdd8/'");
                conn.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
