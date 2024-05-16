package com.mybot.labs.javabot.service;

import java.sql.Date;
import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.model.JokeCalls;
import com.mybot.labs.javabot.repository.JokesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeService implements JokeServiceInterface{
    private final JokesRepository jokesRepository;

    @Override
    public Page<Joke> getJokes(int page) {
        int size = 5;
        return jokesRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Joke> getJokeById(Long id) {

        Optional<Joke> calledJoke = jokesRepository.findById(id);
        if (calledJoke.isPresent()) {
            JokeCalls jokeCall = new JokeCalls(null, calledJoke.get(), null, LocalDateTime.now());
            calledJoke.get().getCalls().add(jokeCall);
            jokesRepository.save(calledJoke.get());
        }
        return calledJoke;
    }

    @Override
    public void createJoke(Joke joke) {
        jokesRepository.save(joke);
    }

    @Override
    public void updateJoke(Long id, String newJoke) {
        Optional<Joke> joke= jokesRepository.findById(id);
            if (joke.isPresent()) {
                joke.get().setText(newJoke) ;
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
