package com.miti99.simplechatbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class SimpleChatBot extends TelegramLongPollingBot {
  public SimpleChatBot() {
    super(System.getenv("BOT_TOKEN"));
  }

  public static void main(String[] args) {
    SimpleChatBot bot = new SimpleChatBot();
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(bot);
      System.out.println("Bot started successfully!");
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      String chatId = update.getMessage().getChatId().toString();
      String inputText = update.getMessage().getText();
      String strikethroughText = "<strike>" + inputText + "</strike>";

      SendMessage message = new SendMessage();
      message.setChatId(chatId);
      message.setText(strikethroughText);
      message.enableHtml(true);

      try {
        execute(message);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {
    return "miti99bot";
  }
}
