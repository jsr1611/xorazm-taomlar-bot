package enums.BotState;

public enum BotState {
    START,
    MENU,
    PRODUCTS,
    CATEGORIES;

    public static BotState fromString(String name){
        for (BotState value : BotState.values()) {
            if(value.name().equals(name)){
                return value;
            }
        }
        return null;
    }

}
