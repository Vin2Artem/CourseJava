package DAO;

import org.sqlite.JDBC;

import java.sql.*;

public class SQLiteDAOFactory extends DAOFactory {
    private static final String DBURL= "jdbc:sqlite:../databases/myfin.db";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            DriverManager.registerDriver(new JDBC());
            connection = DriverManager.getConnection(DBURL);
        }
        return connection;
    }

    public UserDAO getUserDAO() {
        return new SQLiteUserDAO();
    }

    public FeedbackDAO getFeedbackDAO() {
        return new SQLiteFeedbackDAO();
    }

    public SQLiteLessonDAO getLessonDAO() {
        return new SQLiteLessonDAO();
    }

    public SQLiteCourseDAO getCourseDAO() {
        return new SQLiteCourseDAO();
    }
}