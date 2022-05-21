package DAO;

import models.UserCourse;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteUserCourseDAO implements UserCourseDAO {
    public SQLiteUserCourseDAO() {
        // инициализация
    }

    public ArrayList<UserCourse> getAvailableUserCourses(int userId) {
        try {
            ArrayList<UserCourse> lst = new ArrayList<>();
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM user_courses WHERE user = ?");
            pStatement.setObject(1, userId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                UserCourse userCourse = new UserCourse(
                        resultSet.getInt("id"),
                        resultSet.getInt("user"),
                        resultSet.getInt("course"),
                        resultSet.getString("startdate")
                        );
                lst.add(userCourse);
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}