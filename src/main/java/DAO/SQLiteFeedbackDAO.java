package DAO;

import models.Feedback;
import models.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteFeedbackDAO implements FeedbackDAO {
    public SQLiteFeedbackDAO() {
        // инициализация
    }

    public int insertFeed(Feedback feedback) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT id FROM feedbacks WHERE user = ? and topic = ? and desc = ?");
            pStatement.setObject(1, feedback.getUser());
            pStatement.setObject(2, feedback.getTopic());
            pStatement.setObject(3, feedback.getDesc());
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return ALREADY_IS;
            }
            pStatement = connection.prepareStatement(
                    "INSERT INTO feedbacks(`user`, `topic`, `desc`) VALUES(?, ?, ?)");
            pStatement.setObject(1, feedback.getUser());
            pStatement.setObject(2, feedback.getTopic());
            pStatement.setObject(3, feedback.getDesc());
            pStatement.execute();

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(id) AS mx FROM feedbacks");
            if (resultSet.next()) {
                return resultSet.getInt("mx");
            }
            return INSERT_ERROR;
        } catch (SQLException e) {
            e.printStackTrace();
            return INSERT_ERROR;
        }
    }

    public ArrayList<Feedback> getAllFeeds() {
        try {
            ArrayList<Feedback> lst = new ArrayList<>();
            Connection connection = SQLiteDAOFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM feedbacks");
            while (resultSet.next()) {
                Feedback feedback = new Feedback(
                        resultSet.getInt("id"),
                        resultSet.getInt("user"),
                        resultSet.getString("topic"),
                        resultSet.getString("desc")
                );
                lst.add(feedback);
            }
            return lst;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}