package Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotRequestHandler implements Runnable {

    private final TelegramBot telegramBot;
    private final Update update;



    public BotRequestHandler(TelegramBot telegramBot, Update update){
        this.telegramBot = telegramBot;
        this.update = update;
    }


    @Override
    public void run() {

        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        String tgUsername = update.getMessage().getFrom().getUserName();

        switch (message){

            case "/start" -> telegramBot.selectClass(chatId);

            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" -> telegramBot.selectClassType(chatId, message);

            case "А", "Б", "В" -> telegramBot.endReg(message,chatId,tgUsername);

            default -> telegramBot.wrongMessage(chatId);
        }

    }






}
