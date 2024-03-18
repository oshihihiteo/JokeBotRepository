package com.mybot.labs.javabot.service;

import com.mybot.labs.javabot.model.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeServiceInterface {

    List<Joke> getJokes();
    Optional<Joke> getJokeById(Long id);
    void createJoke(Joke joke);
    void updateJoke(Long id, String newJoke);
    void deleteJoke(Long id);
}
