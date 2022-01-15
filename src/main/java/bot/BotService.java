package bot;

import enums.BotState.BotState;
import model.Category;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.CategoryService;
import service.CategoryServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.BotConstants;
import util.BotMenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BotService {
    private static final UserService userService = new UserServiceImpl();
    private static final CategoryService categoryService = new CategoryServiceImpl();

    public static SendMessage start(Update update) throws SQLException {
        //reg new user
        registerUser(update);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(BotConstants.MENU_HEADER);
        sendMessage.setReplyMarkup(getMenuKeyboard());

        return sendMessage;
    }


    private static ReplyKeyboard getMenuKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton(BotMenu.MENU));
        rows.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton(BotMenu.CART));
        keyboardRow2.add(new KeyboardButton(BotMenu.SETTINGS));
        rows.add(keyboardRow2);


        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
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

    public static SendPhoto showProductInfoById(Message message, long productId){
//        model.User user = userService.findByCharId(message.getChatId());
//        if(user != null ){
//            user.setBotState(BotState.PRODUCTS);
//            userService.update(user);
//        }
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());

//        Product product = productService.findById(productId);
//        sendPhoto.setPhoto(new InputFile(product.getImageUrl()));
//        sendPhoto.setCaption(product.getName() + "\n\nPrice: " + product.getPrice() + "so'm\n\nMiqdorni tanlang:");
//
//        sendPhoto.setReplyMarkup(getInlineKeyboardsForOrder(productId));

        return sendPhoto;
    }

    private static ReplyKeyboard getInlineKeyboardsForOrder(Long productId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();

        int count = 1;
        for(int i=0; i < 3; i++){
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            for(int j=0; j < 3; j++){
                InlineKeyboardButton button = new InlineKeyboardButton(count + " ta");
                button.setCallbackData("amount/" + productId + "/" + count);
                buttons.add(button);
                count++;
            }
            inlineKeyboards.add(buttons);
        }
        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);

        return inlineKeyboardMarkup;
    }

    public static SendMessage menu(Long chatId) {
        model.User user = userService.findByChatId(chatId);
        if(user != null){
            user.setBotState(BotState.MENU);
            userService.update(user);
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(BotConstants.MENU_HEADER);

        List<Category> categories = categoryService.findAll();

        sendMessage.setReplyMarkup(getInlineKeyboards(categories));
        return sendMessage;
    }

    private static ReplyKeyboard getInlineKeyboards(List<Category> categories) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();
        List<InlineKeyboardButton> buttons;

        Iterator<Category> iterator = categories.iterator();
        while (iterator.hasNext()){
            Category category = iterator.next();
            buttons = new ArrayList<>();
            InlineKeyboardButton button1 = new InlineKeyboardButton(category.getPrefix() + " " + category.getName());
            button1.setCallbackData(category.getId().toString());
            buttons.add(button1);

            if(iterator.hasNext()){
                InlineKeyboardButton button2 = new InlineKeyboardButton(category.getPrefix() + " " + category.getName());
                button2.setCallbackData(category.getId().toString());
                buttons.add(button2);
            }
            inlineKeyboards.add(buttons);
        }

        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);
        return inlineKeyboardMarkup;
    }
}
