package com.mybot.labs.javabot.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.mybot.labs.javabot.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
