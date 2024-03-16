package com.mybot.labs.javabot.repository;

import com.mybot.labs.javabot.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokesRepository extends JpaRepository<Joke, Long> {
}
