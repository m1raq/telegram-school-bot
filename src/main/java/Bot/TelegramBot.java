package Bot;
import Connection.AddStudentToSQL;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {



    private String regClass;
    private int regYear;

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

        Thread requestHandlerThread = new Thread(new BotRequestHandler(this, update));
        requestHandlerThread.start();
    }



    protected void removeKeyboard(String chatId){
        regYear = 0;
        regClass = "";

        ReplyKeyboardRemove removeKeyboard = new ReplyKeyboardRemove();
        removeKeyboard.setRemoveKeyboard(true);

        SendMessage removeKeyboardResponse = new SendMessage();
        removeKeyboardResponse.setChatId(chatId);
        removeKeyboardResponse.setText("Вы успешно зарегистрированы!");
        removeKeyboardResponse.setReplyMarkup(removeKeyboard);


        try {
            execute(removeKeyboardResponse);
        } catch (TelegramApiException e) {
            e.getCause();
        }

    }

    protected void selectClass(String chatId){

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
            e.getCause();
        }

    }

    protected void selectClassType(String chatId, String message){

        regYear = Integer.parseInt(message);

        ReplyKeyboardMarkup letterKeyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> letterOptions = new ArrayList<>();

        KeyboardRow letterRow = new KeyboardRow();
        letterRow.add(new KeyboardButton("А"));
        letterRow.add(new KeyboardButton("Б"));
        letterRow.add(new KeyboardButton("В"));
        letterOptions.add(letterRow);

        letterKeyboard.setKeyboard(letterOptions);

        SendMessage letterResponse = new SendMessage();
        letterResponse.setChatId(chatId);
        letterResponse.setText("Выберите букву: ");
        letterResponse.setReplyMarkup(letterKeyboard);


        try {
            execute(letterResponse);
        } catch (TelegramApiException e) {
            e.getCause();
        }
    }

    protected void endReg(String message, String chatId, String tgUsername){
        regClass = message;

        AddStudentToSQL.add(regYear, regClass, tgUsername);

        regYear = 0;
        regClass = "";


        removeKeyboard(chatId);
    }

    protected void wrongMessage(String chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Неизвестная команда");
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    protected void hello(String chatId, Update update){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("С возвращением, " + update.getMessage().getFrom().getFirstName());
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}


