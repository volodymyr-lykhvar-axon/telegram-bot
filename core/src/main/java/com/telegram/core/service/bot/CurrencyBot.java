package com.telegram.core.service.bot;

import com.telegram.common.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Currency Bot.
 *
 * @author Volodyr Lykhvar
 */
@Service
public class CurrencyBot extends TelegramLongPollingBot {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyBot.class);

    @Autowired
    private ConfigurationManager configurationManager;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var massage = new SendMessage();
            massage.setChatId(update.getMessage().getChatId());
            massage.setText("When user joins the bot, the bot checks if the max users count doesn't exceed, " +
                    "shows the list of currencies with pagination and maybe with search." +
                    "The user can pick different currencies and at the same time system will store this information in " +
                    "user_currency table and return the current price for selected currencies");
            execute(massage);
        } catch (TelegramApiException e) {
           LOG.error("Can't send message to chat, reason: {}", e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return configurationManager.getSettings().getBotName();
    }

    @Override
    public String getBotToken() {
        return configurationManager.getSettings().getBotToken();
    }
}
