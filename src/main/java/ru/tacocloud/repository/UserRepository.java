package ru.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;

import ru.tacocloud.model.auth.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
