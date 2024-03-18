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
    public List<Joke> getJokes() {
        return jokesRepository.findAll();
    }

    @Override
    public Optional<Joke> getJokeById(Long id) {
        return jokesRepository.findById(id);
    }

    @Override
    public void createJoke(Joke joke) {
        jokesRepository.save(joke);
    }

    @Override
            //Изменить (сейчас тройная проверка, сделать просто получение (Optional) и дальше уже с ним делать
    public void updateJoke(Long id, String new_joke) {
        Optional<Joke> joke= jokesRepository.findById(id);
            if (joke.isPresent()) {
                joke.get().setText(new_joke) ;
                joke.get().setUpdated(new java.sql.Date(System.currentTimeMillis()));
                jokesRepository.save(joke.get());
            }
         else {
            jokesRepository.findById(id);
        }
    }

    @Override
    public void deleteJoke(Long id) {
        jokesRepository.deleteById(id);
    }
}
