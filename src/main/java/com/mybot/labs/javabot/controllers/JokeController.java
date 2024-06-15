package com.mybot.labs.javabot.controllers;

import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.service.JokeService;
import com.mybot.labs.javabot.service.JokeServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
@Slf4j
public class JokeController {
    private final JokeServiceInterface jokeService;

    @PostMapping
    public ResponseEntity<Void> createJoke (@RequestBody Joke joke){
    jokeService.createJoke(joke);
    return ResponseEntity.ok().build();
}
@GetMapping
    public ResponseEntity<Page<Joke>> getJokes(
            @RequestParam int page
){
        return ResponseEntity.ok(jokeService.getJokes(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        Optional<Joke> jokeOptional = jokeService.getJokeById(id);
        if (jokeOptional.isPresent()) {
            return ResponseEntity.ok(jokeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable Long id){
        jokeService.deleteJoke(id);
        return ResponseEntity.ok().build();
        }
@PutMapping("/{id}")
    public ResponseEntity<Void> updateJoke(@PathVariable Long id, @RequestBody String newJoke){
        jokeService.updateJoke(id, newJoke);
        return ResponseEntity.ok().build();
    }
}
