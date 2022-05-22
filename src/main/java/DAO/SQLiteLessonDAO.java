package DAO;

import models.Lesson;
import models.UserCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

            long currentNum = 0;
            LocalDate now = java.time.LocalDate.now();
            LocalDate start = LocalDate.parse(foundUserCourse.getStartDate());
            while (resultSet.next()) {
                LocalDate unlockDate = start.plusDays(currentNum * Lesson.PERIOD);
                Lesson lesson = new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getInt("course"),
                        resultSet.getString("name"),
                        resultSet.getString("desc"),
                        resultSet.getString("url"),
                        ChronoUnit.DAYS.between(now, unlockDate)
                );
                lst.add(lesson);
                currentNum++;
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lesson getAvailableLesson(int userId, int lessonId) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE id = ?");
            pStatement.setObject(1, lessonId);
            ResultSet resultSet = pStatement.executeQuery();
            /* if lesson is not found */
            if (!resultSet.next()) {
                return null;
            }
            int courseId = resultSet.getInt("course");
            Lesson lesson = new Lesson(
                    resultSet.getInt("id"),
                    courseId,
                    resultSet.getString("name"),
                    resultSet.getString("desc"),
                    resultSet.getString("url"),
                    0
            );
            if (isLessonAvailable(userId, lesson)) {
                return lesson;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isLessonAvailable(int userId, Lesson lesson) {
        ArrayList<Lesson> availableLessons = getAvailableLessons(userId, lesson.getCourse());
        for (Lesson lesson1 : availableLessons) {
            if (lesson1.getId() == lesson.getId()) {
                long days = lesson1.getDaysToUnlock();
                if (days > 0) {
                    return false;
                } else {
                    lesson.setDaysToUnlock(days);
                    return true;
                }
            }
        }
        return false;
    }
}