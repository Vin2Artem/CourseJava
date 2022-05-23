package DAO;

import models.Lesson;
import models.User;
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

    public ArrayList<Lesson> getLessonsOfCourse(User user, int courseId) {
        try {
            ArrayList<Lesson> lst = new ArrayList<>();
            LocalDate now = java.time.LocalDate.now();
            /* for editor all courses are opened today */
            LocalDate start = now;
            if (!user.getEditor()) {
                UserCourse foundUserCourse = null;
                SQLiteUserCourseDAO sqLiteUserCourseDAO = new SQLiteUserCourseDAO();
                ArrayList<UserCourse> availableUserCourses = sqLiteUserCourseDAO.getAvailableUserCourses(user.getId());
                for (UserCourse availableUserCourse : availableUserCourses) {
                    if (availableUserCourse.getCourse() == courseId) {
                        foundUserCourse = availableUserCourse;
                        break;
                    }
                }
                if (foundUserCourse == null) {
                    start = now.plusDays(1);
                } else {
                    start = LocalDate.parse(foundUserCourse.getStartDate());
                }
            }
            Connection connection = SQLiteDAOFactory.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM lessons WHERE course = ?");
            pStatement.setObject(1, courseId);
            ResultSet resultSet = pStatement.executeQuery();

            long currentNum = 0;
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

    public Lesson getLessonById(User user, int lessonId) {
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
            ArrayList<Lesson> lessonsOfCourse = getLessonsOfCourse(user, lesson.getCourse());
            for (Lesson l : lessonsOfCourse) {
                if (l.getId() == lesson.getId()) {
                    lesson.setDaysToUnlock(l.getDaysToUnlock());
                    break;
                }
            }
            return lesson;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Lesson getNextAvailableLesson(User user, Lesson lesson) {
        ArrayList<Lesson> lessonsOfCourse = getLessonsOfCourse(user, lesson.getCourse());
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        for (Lesson l : lessonsOfCourse) {
            if (l.isUnlocked(user)) {
                availableLessons.add(l);
            }
        }
        boolean isFound = false;
        for (Lesson l : availableLessons) {
            if (isFound) {
                return l;
            }
            if (l.getId() == lesson.getId()) {
                isFound = true;
            }
        }
        if (isFound) {
            return availableLessons.get(0);
        }
        return null;
    }

    public Lesson getPrevAvailableLesson(User user, Lesson lesson) {
        ArrayList<Lesson> lessonsOfCourse = getLessonsOfCourse(user, lesson.getCourse());
        ArrayList<Lesson> availableLessons = new ArrayList<>();
        for (Lesson l : lessonsOfCourse) {
            if (l.isUnlocked(user)) {
                availableLessons.add(l);
            }
        }
        Lesson prev = availableLessons.get(availableLessons.size() - 1);
        for (Lesson l : availableLessons) {
            if (l.getId() == lesson.getId()) {
                return prev;
            }
            prev = l;
        }
        return null;
    }

    public boolean editLesson(int courseId, String lessonDesc, String url) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "UPDATE lessons SET desc = ?, url = ? WHERE id = ?");
            pStatement.setObject(1, lessonDesc);
            pStatement.setObject(2, url);
            pStatement.setObject(3, courseId);
            pStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}