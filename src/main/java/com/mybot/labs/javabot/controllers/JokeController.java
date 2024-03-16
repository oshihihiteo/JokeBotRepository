package com.mybot.labs.javabot.controllers;

import com.mybot.labs.javabot.model.Joke;
import com.mybot.labs.javabot.service.JokeService;
import com.mybot.labs.javabot.service.JokeServiceInterface;
import lombok.RequiredArgsConstructor;
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
    ResponseEntity<Void> create_joke (@RequestBody Joke joke){
    jokeService.create_joke(joke);
    return ResponseEntity.ok().build();
}
@GetMapping
    ResponseEntity<List<Joke>> get_jokes(){
        return ResponseEntity.ok(jokeService.get_jokes());
    }
    @GetMapping("/{id}")
    ResponseEntity<Joke> get_joke_by_id(@PathVariable Long id) {
        Optional<Joke> jokeOptional = jokeService.get_joke_by_id(id);
        if (jokeOptional.isPresent()) {
            return ResponseEntity.ok(jokeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete_joke(@PathVariable Long id){
        jokeService.delete_joke(id);
        return ResponseEntity.ok().build();
        }
@PutMapping("/{id}")
    ResponseEntity<Void> update_joke(@PathVariable Long id, @RequestBody String new_joke){
        jokeService.update_joke(id, new_joke);
        return ResponseEntity.ok().build();
    }
}
