package com.example.applicationtask.repository;

import com.example.applicationtask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findAllByBirthDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
