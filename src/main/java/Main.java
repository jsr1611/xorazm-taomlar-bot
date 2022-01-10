import bot.AppBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.StoreDataToDbFromJson;

public class Main {
    public static void main(String[] args) {
        StoreDataToDbFromJson.store();
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new AppBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
