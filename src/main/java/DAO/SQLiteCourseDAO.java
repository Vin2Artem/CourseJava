package DAO;

import models.Course;
import models.Feedback;
import models.User;

import java.sql.*;

public class SQLiteCourseDAO implements CourseDAO {
    public SQLiteCourseDAO() {
        // инициализация
    }

    public Course findCourse(int courseId) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM courses WHERE id = ?");
            pStatement.setObject(1, courseId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return new Course(
                        courseId,
                        resultSet.getString("name"),
                        resultSet.getString("desc")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}