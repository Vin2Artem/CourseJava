package DAO;

import models.UserCourse;

import java.util.ArrayList;

public interface UserCourseDAO {
    int INSERT_ERROR = -1;

    //int insertUserCourse(UserCourse userCourse);
    //boolean deleteUserCourse(int id);
    //boolean updateUserCourse(UserCourse userCourse);
    //UserCourse findUserCourse(int userId);
    ArrayList<UserCourse> getAvailableUserCourses(int userId);
    //ArrayList<UserCourse> getAllUserCourses();
}
