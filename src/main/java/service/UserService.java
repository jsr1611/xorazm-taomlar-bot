package service;

import model.User;

import java.sql.SQLException;

public interface UserService {
    void save(User user) throws SQLException;

    boolean existsByChatId(Long chatId) throws SQLException;
}
