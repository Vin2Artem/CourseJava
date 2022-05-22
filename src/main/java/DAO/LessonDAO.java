package DAO;

import models.Lesson;

import java.util.ArrayList;

public interface LessonDAO {
    int INSERT_ERROR = -1;

    //int insertUserCourse(UserCourse userCourse);
    //boolean deleteUserCourse(int id);
    //boolean updateUserCourse(UserCourse userCourse);
    //UserCourse findUserCourse(int userId);
    ArrayList<Lesson> getLessonsOfCourse(int userId, int courseId);
    Lesson getLessonById(int userId, int lessonId);
    Lesson getNextAvailableLesson(int userId, Lesson lesson);
    Lesson getPrevAvailableLesson(int userId, Lesson lesson);
    //ArrayList<UserCourse> getAllUserCourses();
}
