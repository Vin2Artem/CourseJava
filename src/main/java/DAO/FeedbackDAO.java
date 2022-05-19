package DAO;

import models.Feedback;

import java.util.ArrayList;

public interface FeedbackDAO {
    int INSERT_ERROR = -1;
    int ALREADY_IS = -2;

    int insertFeed(Feedback feedback);
}
