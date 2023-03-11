package com.security.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.practice.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findByEmail(String email);

}
