package services;

/**
 * Created by antonandreev on 06/07/2017.
 */

import DAO.ScheduleDAO;
import org.json.simple.JSONArray;

import java.sql.*;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class H2DBServiceImpl implements H2DBService {

    private static volatile H2DBServiceImpl dbService;
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

    public static H2DBServiceImpl getInstance() {
        if (dbService == null) {
            synchronized (H2DBServiceImpl.class) {
                if (dbService == null) {
                    dbService = new H2DBServiceImpl();
                }
            }
        }
        return dbService;
    }

    public JSONArray getJsonArray(String bdName) {
        ScheduleDAO scheduleDAO = new ScheduleDAO(getConnect());
        return scheduleDAO.getJsonArray(bdName);
    }

    public void update(String bdName, int from, int to) {
        ScheduleDAO scheduleDAO = new ScheduleDAO(getConnect());
        scheduleDAO.update(bdName, from, to);
    }

    private Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
