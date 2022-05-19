package DAO;

import models.Feedback;

import java.sql.*;

public class SQLiteFeedbackDAO implements FeedbackDAO {
    public SQLiteFeedbackDAO() {
        // инициализация
    }

    public int insertFeed(Feedback feedback) {
        try {
            Connection connection = SQLiteDAOFactory.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT id FROM feedbacks WHERE user = ? and topic = ? and desk = ?");
            pStatement.setObject(1, feedback.getUser());
            pStatement.setObject(2, feedback.getTopic());
            pStatement.setObject(3, feedback.getDesk());
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return ALREADY_IS;
            }
            pStatement = connection.prepareStatement(
                    "INSERT INTO feedbacks(`user`, `topic`, `desk`) VALUES(?, ?, ?)");
            pStatement.setObject(1, feedback.getUser());
            pStatement.setObject(2, feedback.getTopic());
            pStatement.setObject(3, feedback.getDesk());
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
}