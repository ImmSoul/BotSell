package mindigulov.bot.botsell;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class Bot extends TelegramLongPollingBot {

    SQL sql;
    Integer userId;
    User user;

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
        user = new User(update.getMessage().getChat().getId(), update.getMessage().getChat().getFirstName(), update.getMessage().getChat().isUserChat());

        try {
          if (sql.checkId(user.getId())) {
              System.out.println("ЮЗЕР ЕСТЬ");
          } else {
              starting(user, update.getCallbackQuery());
              System.out.println("ЮЗЕР НЕТЬ!");
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void starting(User user, CallbackQuery callbackQuery) throws TelegramApiException, InterruptedException {
        SendMessage sendMessage = new SendMessage(user.getId().toString(), "Привет! Это чат с результатами твоих продаж! Давай познакомимся, укажи свою имя и фамилию в формате Имя_Фамилия. Например Алексей_Петров");
        execute(sendMessage);
        while (callbackQuery == null) {
            Thread.sleep(1000);
        }

        System.out.println(callbackQuery.getData());


        System.out.println("call bak is = " + callbackQuery.getMessage());
    }




}
