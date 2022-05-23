package DAO;

import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SQLiteCourseDAO implements CourseDAO {
    public SQLiteCourseDAO() {
        // инициализация
    }

    public ArrayList<Course> getAllCourses() {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();
            ArrayList<Course> courses = new ArrayList<>();
            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM courses");
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("desc")
                ));
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

    public boolean editCourse(int courseId, String courseDesc) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "UPDATE courses SET desc = ? WHERE id = ?");
            pStatement.setObject(1, courseDesc);
            pStatement.setObject(2, courseId);
            pStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Course> findCourses(String desc) {
        try {
            ArrayList<Course> courses = new ArrayList<>();

            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * FROM courses");
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("desc").toLowerCase().contains(desc.toLowerCase())) {
                    courses.add(new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("desc")
                    ));
                }
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}