package com.telegram.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Currency Bot.
 *
 * @author Volodyr Lykhvar
 */
@Component
public class CurrencyBot extends TelegramLongPollingBot {

    @Value("${telegram.settings.bot-name}")
    private String botUsername;

    @Value("${telegram.settings.bot-token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var massage = new SendMessage();
            massage.setChatId(update.getMessage().getChatId());
            massage.setText("hello world");
            execute(massage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
