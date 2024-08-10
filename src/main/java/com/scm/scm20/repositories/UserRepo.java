package com.scm.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm20.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmailToken(String id);
}
