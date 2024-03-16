package com.mybot.labs.javabot.service;

import java.sql.Date;
import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.repository.JokesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeService implements JokeServiceInterface{
    private final JokesRepository jokesRepository;

    @Override
    public List<Joke> get_jokes() {
        return jokesRepository.findAll();
    }

    @Override
    public Optional<Joke> get_joke_by_id(Long id) {
        return jokesRepository.findById(id);
    }

    @Override
    public void create_joke(Joke joke) {
        jokesRepository.save(joke);
    }

    @Override
    public void update_joke(Long id, String new_joke) {
        if (jokesRepository.existsById(id)) {
            Joke joke = jokesRepository.findById(id).orElse(null);
            if (joke != null) {
                joke.setText(new_joke) ;
                joke.setUpdated(new java.sql.Date(System.currentTimeMillis()));
                jokesRepository.save(joke);
            }
        } else {
            jokesRepository.findById(id);
        }
    }

    @Override
    public void delete_joke(Long id) {
        jokesRepository.deleteById(id);
    }
}
