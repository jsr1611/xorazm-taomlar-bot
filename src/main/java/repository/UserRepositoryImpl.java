package repository;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.DbConfig.connection;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean existsByChatId(Long chatId) throws SQLException {
        String SELECT_USER_BY_CHAT_ID = "SELECT * FROM users WHERE chat_id = " + chatId;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_CHAT_ID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(User user) throws SQLException {
        String INSERT_NEW_USER = "INSERT INTO users(chat_id, first_name, last_name, user_name, phone_number, bot_state) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER);

        statement.setLong(1, user.getChatId());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getUserName());
        statement.setString(5, user.getPhoneNumber());
        statement.setString(6, user.getBotState().name());

        statement.executeQuery();
    }

    @Override
    public boolean existsById(Long id) {
        String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = " + id;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
