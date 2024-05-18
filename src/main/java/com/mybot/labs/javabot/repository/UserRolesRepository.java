package com.mybot.labs.javabot.repository;

import org.springframework.data.repository.CrudRepository;
import com.mybot.labs.javabot.model.UserRole;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
