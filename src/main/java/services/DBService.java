package services;

/**
 * Created by antonandreev on 06/07/2017.
 */
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class DBService {
    private static volatile DBService dbService;
    private static final String USER = "root";
    private static final String PASS = "olz3hy4h";
    private static final String DB_URL = "jdbc:mysql://localhost/schedule?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setUrl(DB_URL);
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

    public List<String> getNearFrom() {
        String sql = "SELECT time FROM bus WHERE time > '" + getCurrentTime() + ":00'" + " AND (office = 'ot' OR office = 'ot/do') LIMIT 3";
        return getNear(sql);
    }

    public List<String> getNearTo() {
        String sql = "SELECT time FROM bus WHERE time > '" + getCurrentTime() + ":00'" + " AND (office = 'do' OR office = 'ot/do') LIMIT 3";
        return getNear(sql);
    }

    private List<String> getNear(String sql) {
        List<String> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result.add(rs.getString("time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        Date date = new Date();
        return dateFormat.format(date);
    }
}
