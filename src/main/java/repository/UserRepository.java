package repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.User;

import java.sql.SQLException;

public interface UserRepository {
    boolean existsByChatId(Long chatId) throws SQLException;

    void save(User user) throws SQLException;

    boolean existsById(Long id);
}
