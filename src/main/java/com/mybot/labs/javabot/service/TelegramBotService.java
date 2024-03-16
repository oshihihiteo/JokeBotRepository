package com.mybot.labs.javabot.service;

import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.repository.JokesRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TelegramBotService  {
    private final JokesRepository jokesRepository;

    private final TelegramBot telegramBot; //Забираем наш бин с ботом из Spring-а

    public TelegramBotService(JokesRepository jokesRepository, @Autowired TelegramBot telegramBot) {
        this.jokesRepository = jokesRepository; //Конструктор для вставки бина
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(updates -> { //Лямбда - регистрируем слушателя обновлений
            updates.forEach(this::buttonClickReact); //В лямбде забираем все обновления - и вызываем обработку их
            return UpdatesListener.CONFIRMED_UPDATES_ALL; //Подтверждаем, что все забрали
        }, Throwable::printStackTrace); //Если поймали ошибку - выводим трейс, чтобы понять, в чем дело
    }

    private void buttonClickReact(Update update) { //Реагируем на событие
        if (update.message() != null && update.message().text() != null) {
            String messageText = update.message().text();

            if (messageText.startsWith("/help")) {
                SendMessage request = new SendMessage(update.message().chat().id(), "Команды для работы с ботом:\n\n/joke - выдача случайной шутки")
                        .disableNotification(true);
                this.telegramBot.execute(request); //Отправляем подготовленное сообщение
            }
            else if (messageText.startsWith("/joke")) {
                Random rand = new Random();
                Joke joke = jokesRepository.findById(Long.valueOf(rand.nextInt(1, 16))).get();
                SendMessage request = new SendMessage(update.message().chat().id(), joke.getText())
                        .disableNotification(true);
                this.telegramBot.execute(request); //Отправляем подготовленное сообщение
            }
        }
    }

}
