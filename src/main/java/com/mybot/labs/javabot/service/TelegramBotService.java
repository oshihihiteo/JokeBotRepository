package com.mybot.labs.javabot.service;

import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.model.JokeCalls;
import com.mybot.labs.javabot.repository.JokesRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    protected void buttonClickReact(Update update) { //Реагируем на событие
        if (update.message() != null && update.message().text() != null) {
            String messageText = update.message().text();
            Long chatId = update.message().chat().id();

            if (messageText.startsWith("/help")) {
                SendMessage request = new SendMessage(chatId, "Команды для работы с ботом:\n\n/joke - выдача случайной шутки\n/top - 5 самых популярных анекдотов")
                        .disableNotification(true);
                this.telegramBot.execute(request); //Отправляем подготовленное сообщение
            }

            else if (messageText.startsWith("/joke")) {
                Joke joke = jokesRepository.findRandomJoke();
                SendMessage request = new SendMessage(chatId, joke.getText())
                            .disableNotification(true);
                    this.telegramBot.execute(request); //Отправляем подготовленное сообщение
                
                    JokeCalls jokeCall = new JokeCalls(null, joke, chatId, LocalDateTime.now());
                    joke.getCalls().add(jokeCall);
                    jokesRepository.save(joke);
            } else if (messageText.startsWith("/top")) {
                List<Joke> jokes = jokesRepository.topJokes();
                String jokesMessage = "Топ 5 анекдотов: \n";
                for (int i = 0; i < jokes.size(); i++){
                    String s = (i + 1) +". " + jokes.get(i).getText() + "\n";
                    jokesMessage = jokesMessage + s;
                }
                SendMessage request = new SendMessage(chatId, jokesMessage)
                        .disableNotification(true);
                this.telegramBot.execute(request); //Отправляем подготовленное сообщение
            }
        }
    }

}
