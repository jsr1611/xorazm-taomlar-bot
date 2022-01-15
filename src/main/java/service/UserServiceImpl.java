package service;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private static final UserRepository userRepo = new UserRepositoryImpl();

    @Override
    public void save(User user) throws SQLException {
        if(!userRepo.existsById(user.getId())){
            userRepo.save(user);
        }
    }

    @Override
    public boolean existsByChatId(Long chatId) throws SQLException {
        return userRepo.existsByChatId(chatId);
    }

    @Override
    public User findByChatId(Long chatId) {
        return userRepo.findByChatId(chatId);
    }

    @Override
    public void update(User user) {
        userRepo.update(user);
    }
}
