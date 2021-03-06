package DAO;

import models.User;

import java.util.*;

public interface UserDAO {
    int INSERT_ERROR = -1;
    int UNIQUE_EMAIL = -2;

    int insertUser(User user);
    boolean deleteUser(int id);
    boolean editUser(User user, String surname, String name, String patronymic, String email, String password, String phone, String city, Boolean isEditor);
    User findUser(int userId);
    User findUser(String email, String password);
    ArrayList<User> getAllUsers();
}
