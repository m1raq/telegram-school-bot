package Bot;
import UsersData.GroupType;
import UsersData.Registration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "mboutso_bot";
    }

    @Override
    public String getBotToken() {
        return "6377557658:AAHyFXrm6TiONTHt8Y7TV0pKw2m7FBCh4gw";
    }

    @Override
    public void onUpdateReceived(Update update) {

        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();

        switch (message){

            case "/start" -> {

                int group;
                GroupType groupType;

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton("1"));
                row.add(new KeyboardButton("2"));
                row.add(new KeyboardButton("3"));
                row.add(new KeyboardButton("4"));
                row.add(new KeyboardButton("5"));
                row.add(new KeyboardButton("6"));
                row.add(new KeyboardButton("7"));
                row.add(new KeyboardButton("8"));
                row.add(new KeyboardButton("9"));
                row.add(new KeyboardButton("10"));
                row.add(new KeyboardButton("11"));
                keyboard.add(row);

                keyboardMarkup.setKeyboard(keyboard);

                SendMessage response = new SendMessage();
                response.setChatId(chatId);
                response.setText("Выберите класс: ");
                response.setReplyMarkup(keyboardMarkup);


                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                String selectedValue = update.getMessage().getText();
                switch (selectedValue){

                    case "1" -> {

                        ReplyKeyboardMarkup typeMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> type = new ArrayList<>();

                        KeyboardRow buttons = new KeyboardRow();
                        buttons.add(new KeyboardButton("А"));
                        buttons.add(new KeyboardButton("Б"));
                        buttons.add(new KeyboardButton("В"));
                        type.add(buttons);


                        SendMessage response1 = new SendMessage();
                        response1.setChatId(chatId);
                        response1.setText("Выберите класс: ");
                        response1.setReplyMarkup(keyboardMarkup);


                        try {
                            execute(response1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

                }








            }

            case "привет" -> {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Привет как твои дела?");
                try {
                    this.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            case "пока" -> {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("До встречи");
                try {
                    this.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

            default -> {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Неизвестная команда");
                try {
                    this.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

        }




    }
}
