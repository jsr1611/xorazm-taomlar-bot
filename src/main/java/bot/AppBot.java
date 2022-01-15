package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import util.BotMenu;
import util.BotSettings;

public class AppBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return BotSettings.USER_NAME_BOT;
    }

    @Override
    public String getBotToken() {
        return BotSettings.TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            // user responses
            Message message = update.getMessage();
            SendMessage sendMessage = null;

            if(message.hasText()){
                String text = message.getText();
                switch (text){
                    case BotMenu.START:
                        sendMessage = BotService.start(update);
                        break;
                    case BotMenu.MENU:
                        sendMessage = BotService.menu(update.getMessage().getChatId());
                        break;


                }



            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if(update.hasCallbackQuery()){
            // in-line
        }
    }
}
