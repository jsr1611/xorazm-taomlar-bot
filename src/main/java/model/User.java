package model;

import enums.BotState.BotState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private BotState botState;
}
