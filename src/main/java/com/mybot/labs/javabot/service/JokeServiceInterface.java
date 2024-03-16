package com.mybot.labs.javabot.service;

import com.mybot.labs.javabot.model.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeServiceInterface {

    List<Joke> get_jokes();
    Optional<Joke> get_joke_by_id(Long id);
    void create_joke(Joke joke);
    void update_joke(Long id, String new_joke);
    void delete_joke(Long id);
}
