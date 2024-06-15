package com.mybot.labs.javabot.controllers;

import com.mybot.labs.javabot.model.User;
import com.mybot.labs.javabot.model.UserAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mybot.labs.javabot.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<Void> registration(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        userService.registration(username, password);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserRoles(@PathVariable Long id, @RequestBody UserAuthority authorities) {
        userService.updateUserRoles(id, authorities);
        return ResponseEntity.ok().build();
    }

}
