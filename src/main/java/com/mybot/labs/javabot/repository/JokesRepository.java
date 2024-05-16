package com.mybot.labs.javabot.repository;

import com.mybot.labs.javabot.model.Joke;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JokesRepository extends PagingAndSortingRepository<Joke, Long>, JpaRepository<Joke, Long> {
    @Query(value = "SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Joke findRandomJoke();

    @Query(value = "SELECT jokes.id, jokes.text, jokes.created, jokes.updated FROM jokes JOIN joke_calls ON jokes.id = joke_calls.called_joke GROUP BY jokes.id ORDER BY count(joke_calls.id) DESC LIMIT 5", nativeQuery = true)
    List<Joke> topJokes();
}
