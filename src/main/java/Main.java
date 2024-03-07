import Bot.TelegramBot;
import Connection.ConnectionToNewsSQL;
import Connection.ConnectionToUsersSQL;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());
//        ConnectionToUsersSQL.connection();
//        ConnectionToUsersSQL.connection().close();
//        ConnectionToNewsSQL.connection();
//        ConnectionToNewsSQL.connection().close();
    }

}
