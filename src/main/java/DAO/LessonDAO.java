package DAO;

import models.Lesson;
import models.User;

import java.util.ArrayList;

public interface LessonDAO {
    int INSERT_ERROR = -1;

    //int insertUserCourse(UserCourse userCourse);
    //boolean deleteUserCourse(int id);
    //boolean updateUserCourse(UserCourse userCourse);
    //UserCourse findUserCourse(int userId);
    ArrayList<Lesson> getLessonsOfCourse(User user, int courseId);
    Lesson getLessonById(User user, int lessonId);
    Lesson getNextAvailableLesson(User user, Lesson lesson);
    Lesson getPrevAvailableLesson(User user, Lesson lesson);
    boolean editLesson(int courseId, String lessonDesc, String url);
    //ArrayList<UserCourse> getAllUserCourses();
}
