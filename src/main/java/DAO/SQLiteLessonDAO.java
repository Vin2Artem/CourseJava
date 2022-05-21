package DAO;

import models.Lesson;
import models.UserCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLiteLessonDAO implements LessonDAO {
    public SQLiteLessonDAO() {
        // инициализация
    }

    public ArrayList<Lesson> getAvailableLessons(int userId, int courseId) {
        try {
            ArrayList<Lesson> lst = new ArrayList<>();
            UserCourse foundUserCourse = null;
            SQLiteUserCourseDAO sqLiteUserCourseDAO = new SQLiteUserCourseDAO();
            ArrayList<UserCourse> availableUserCourses = sqLiteUserCourseDAO.getAvailableUserCourses(userId);
            for (UserCourse availableUserCourse : availableUserCourses) {
                if (availableUserCourse.getCourse() == courseId) {
                    foundUserCourse = availableUserCourse;
                    break;
                }
            }
            if (foundUserCourse == null) {
                return null;
            }
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE course = ?");
            pStatement.setObject(1, courseId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getInt("course"),
                        resultSet.getString("name"),
                        resultSet.getString("desc"),
                        resultSet.getString("url")
                        );
                lst.add(lesson);
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}