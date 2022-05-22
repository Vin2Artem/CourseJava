package DAO;

import models.Course;

import java.util.ArrayList;

public interface CourseDAO {
    int INSERT_ERROR = -1;

    //int insertUserCourse(UserCourse userCourse);
    //boolean deleteUserCourse(int id);
    //boolean updateUserCourse(UserCourse userCourse);
    Course findCourse(int courseId);
    boolean editCourse(int courseId, String courseDesc);
    //ArrayList<Course> getAvailableLessons(int userId, int courseId);
    //ArrayList<UserCourse> getAllUserCourses();
}
