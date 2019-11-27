package data;

import java.sql.*;

public class DBManager {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://acrtdata.jacgri9.dreamhosters.com:3306/acrtdata";
    private static Connection conn = null;
    private static Statement stmt = null;

    // Database credentials
    private static final String USER = "cyan";
    private static final String PASS = "Invictusmaneo!@!";

    public DBManager() {
        setup();

    }

    private void setup() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateStatus(String licenceKey, String runtime, String script, String live) {
        String sql = "UPDATE RealTimeData SET run_time = ?, script = ?, live = ? WHERE licence_key = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, runtime);
            preparedStatement.setString(2, script);
            preparedStatement.setString(3, live);
            preparedStatement.setString(4, licenceKey
            );

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTime(String licenceKey, String runtime) {
        String sql = "UPDATE RealTimeData SET run_time = ? WHERE licence_key = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, runtime);
            preparedStatement.setString(2, licenceKey
            );

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}