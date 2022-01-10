package bot;

import enums.BotState.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import service.UserService;
import service.UserServiceImpl;

import java.sql.SQLException;

public class BotService {
    private static final UserService userService = new UserServiceImpl();
    public static SendMessage start(Update update) throws SQLException {
        //reg new user
        registerUser(update);

        return null;
    }

    private static void registerUser(Update update) throws SQLException {
        User from =  update.getMessage().getFrom();
        if(!userService.existsByChatId(update.getMessage().getChatId())) {
            model.User user = new model.User(
            update.getMessage().getChatId(),
                    from.getFirstName(),
                    from.getLastName(),
                    from.getUserName(),
                    update.getMessage().getContact() != null ? update.getMessage().getContact().getPhoneNumber() : "",
                    BotState.START
                    );
            userService.save(user);
        }


    }
}
