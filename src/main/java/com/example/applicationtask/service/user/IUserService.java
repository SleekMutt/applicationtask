package com.example.applicationtask.service.user;

import com.example.applicationtask.dto.PartialUserUpdateRequest;
import com.example.applicationtask.dto.UserCreationRequest;
import com.example.applicationtask.dto.UserUpdateRequest;
import com.example.applicationtask.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserService {
  User createUser(UserCreationRequest user);
  void deleteUser(Long id);
  User getUserById(Long id);
  List<User> getAllUsers();
  User updateUser(UserUpdateRequest user);
  User partialUpdateUser(PartialUserUpdateRequest user);
  List<User> getUsersByBirthDatePeriod(LocalDateTime from, LocalDateTime to);
}
