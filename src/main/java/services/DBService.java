package services;

/**
 * Created by antonandreev on 06/07/2017.
 */

import main.JsonIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.*;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class DBService {
    private static volatile DBService dbService;
    private static final String URL = "jdbc:h2:./h2db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error load H2 JDBC driver: " + e.getMessage());
        }
    }

    public static DBService getInstance() {
        if (dbService == null) {
            synchronized (DBService.class) {
                if (dbService == null) {
                    dbService = new DBService();
                }
            }
        }
        return dbService;
    }

    public JSONArray jsonArrayFromH2db(String bdName) {
        Connection conn = getH2Configuration();
        JSONArray jsonArray = new JSONArray();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT time, mask FROM " + bdName)) {

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                String time = rs.getString("time");
                obj.put("time", time.substring(0, time.length() - 3));
                obj.put("mask", rs.getByte("mask"));
                jsonArray.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }


    private void createSimpleDBSchema(Connection con, String input, String bdName) {
        JsonIO.createH2dbFromJson(input, con, bdName);
    }


    private Connection getH2Configuration() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
