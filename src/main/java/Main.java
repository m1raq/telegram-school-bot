import Bot.TelegramBot;
import Connection.ConnectionToSQL;
import Dto.Teacher;
import org.hibernate.Transaction;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());

//        ConnectionToSQL.connection();
//        ConnectionToSQL.connection().close();

    }


    private static void addTeacher(){

        ConnectionToSQL.connection();
        Transaction transaction = null;

        while (true){
            transaction = ConnectionToSQL.connection().beginTransaction();
            Teacher teacher = new Teacher();
            System.out.println("Введите имя учителя");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Введите кабинет");
            int cab = new Scanner(System.in).nextInt();
            teacher.setName(name);
            teacher.setCabinet(cab);
            ConnectionToSQL.connection().save(teacher);
            transaction.commit();
        }

    }
}
