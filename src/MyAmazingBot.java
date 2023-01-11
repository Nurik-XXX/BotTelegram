import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class MyAmazingBot extends TelegramLongPollingBot {
    boolean booking[] = new boolean[12];
    String names[] = new String[12];
    String message_text1;
    String message_text2;
    String message_text3;
    StringBuilder s;
    int sum;
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            if(message_text.charAt(0) >= 48 && message_text.charAt(0) <= 57) {
                if (booking[Integer.parseInt(message_text)] == false) {
                    booking[Integer.parseInt(message_text)] = true;
                    s = new StringBuilder("on this time room is free,please write name");
                } else s = new StringBuilder("on this time room is busy, by " + names[Integer.parseInt(message_text)]);
            }
            else if(message_text.equals("/start")){
                s = new StringBuilder("Booking times\n1.10:00 - 11:00\n2.11:00 - 12:00\n3.12:00 - 13:00\n4.13:00 - 14:00\n5.14:00-15:00");
            }
            else{
                names[sum] = update.getMessage().getText();
                s = new StringBuilder(names[sum] + " succesfully booked room at " + sum);
            }
            sum = Integer.parseInt(message_text);
            SendMessage message = new SendMessage(); // Create a message object object
            message.setText(new String(s));
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        // TODO
        return "MyFirstBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5724110197:AAFdXZSpfpZ7RKgJOjMxAQsH4yzzcor6cbQ";
    }

}