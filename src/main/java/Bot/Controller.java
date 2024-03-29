package Bot;
import Connection.AddNewsToSQL;
import Connection.ConnectionToSQL;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
public class Controller implements Runnable {

    private final TelegramBot telegramBot;
    private final Update update;


    @Override
    public void run() {

        if(update.hasChannelPost()){
            AddNewsToSQL.add(update);
        } else {
            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText();
            String tgUsername = update.getMessage().getFrom().getUserName();


            if (ConnectionToSQL.connection()
                    .createQuery("SELECT tgUsername FROM Student WHERE tgUsername = '" + tgUsername + "'")
                    .uniqueResult() == null) {

                switch (message) {
                    case "/start" -> telegramBot.selectClass(chatId);
                    case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" -> telegramBot.selectClassType(chatId, message);
                    case "А", "Б", "В" -> telegramBot.endReg(message, chatId, tgUsername);
                    default -> telegramBot.nonReg(chatId);

                }
            } else {
                switch (message) {
                    case "/start" -> telegramBot.hello(chatId, update);
                    case "/news" -> telegramBot.sendLastNews(chatId);
                    case "/teachers" -> telegramBot.sendTeachers(chatId);
                    default -> telegramBot.wrongMessage(chatId);
                }
            }
        }
    }






}
