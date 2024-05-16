package com.mybot.labs.javabot.controllers;

import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.service.JokeService;
import com.mybot.labs.javabot.service.JokeServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {
    private final JokeServiceInterface jokeService;

    @PostMapping
    ResponseEntity<Void> createJoke (@RequestBody Joke joke){
    jokeService.createJoke(joke);
    return ResponseEntity.ok().build();
}
@GetMapping
    ResponseEntity<Page<Joke>> getJokes(
            @RequestParam int page
){
        return ResponseEntity.ok(jokeService.getJokes(page));
    }
    @GetMapping("/{id}")
    ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        Optional<Joke> jokeOptional = jokeService.getJokeById(id);
        if (jokeOptional.isPresent()) {
            return ResponseEntity.ok(jokeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJoke(@PathVariable Long id){
        jokeService.deleteJoke(id);
        return ResponseEntity.ok().build();
        }
@PutMapping("/{id}")
    ResponseEntity<Void> updateJoke(@PathVariable Long id, @RequestBody String newJoke){
        jokeService.updateJoke(id, newJoke);
        return ResponseEntity.ok().build();
    }
}
