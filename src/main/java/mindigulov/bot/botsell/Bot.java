package mindigulov.bot.botsell;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class Bot extends TelegramLongPollingBot {

    SQL sql;

    public Bot() throws SQLException, ClassNotFoundException {
        SQL sql = new SQL();
        this.sql = sql;
    }

    @Override
    public String getBotUsername() {
        return "SellSibset";
    }

    @Override
    public String getBotToken() {
        return "5456452792:AAHhqjLipenbEcb7u7RX4uI0tBaaK0icNvQ";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                String text = update.getMessage().getText();
                sql.setName(text);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
