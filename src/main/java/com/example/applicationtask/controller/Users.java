package com.example.applicationtask.controller;

import com.example.applicationtask.dto.PartialUserUpdateRequest;
import com.example.applicationtask.dto.UserCreationRequest;
import com.example.applicationtask.dto.UserUpdateRequest;
import com.example.applicationtask.entity.User;
import com.example.applicationtask.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/user")
public class Users {
  private final UserServiceImpl userServiceImpl;

  public Users(UserServiceImpl userServiceImpl) {
    this.userServiceImpl = userServiceImpl;
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> getAllUsers()  {
    return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
  }
  @GetMapping("/search-all-by-birthdate-period")
  public ResponseEntity<List<User>> searchAllUsersByBirthdatePeriod(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to)  {
    return new ResponseEntity<>(userServiceImpl.getUsersByBirthDatePeriod(from, to), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id)  {
    return new ResponseEntity<>(userServiceImpl.getUserById(id), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<User> createUser(@Valid@RequestBody UserCreationRequest user)  {
    return new ResponseEntity<>(userServiceImpl.createUser(user), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id)  {
    userServiceImpl.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/")
  public ResponseEntity<User> updateUser(@RequestBody@Valid UserUpdateRequest user)  {
    return new ResponseEntity<>(userServiceImpl.updateUser(user), HttpStatus.OK);
  }

  @PatchMapping("/")
  public ResponseEntity<User> partialUpdateUser(@RequestBody@Valid PartialUserUpdateRequest user)  {
    return new ResponseEntity<>(userServiceImpl.partialUpdateUser(user), HttpStatus.OK);
  }
}
