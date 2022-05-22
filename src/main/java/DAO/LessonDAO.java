package DAO;

import models.Lesson;

import java.util.ArrayList;

public interface LessonDAO {
    int INSERT_ERROR = -1;

    //int insertUserCourse(UserCourse userCourse);
    //boolean deleteUserCourse(int id);
    //boolean updateUserCourse(UserCourse userCourse);
    //UserCourse findUserCourse(int userId);
    ArrayList<Lesson> getAvailableLessons(int userId, int courseId);
    Lesson getAvailableLesson(int userId, int lessonId);
    boolean isLessonAvailable(int userId, Lesson lesson);
    //ArrayList<UserCourse> getAllUserCourses();
}
