package com.example.applicationtask.service.user;

import com.example.applicationtask.dto.PartialUserUpdateRequest;
import com.example.applicationtask.dto.UserCreationRequest;
import com.example.applicationtask.dto.UserUpdateRequest;
import com.example.applicationtask.entity.User;
import com.example.applicationtask.exception.DateViolationException;
import com.example.applicationtask.exception.UnderAgeException;
import com.example.applicationtask.mapper.UserMapper;
import com.example.applicationtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements IUserService{
  @Value("${minimum.age}")
  private int minimumAge;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public User createUser(UserCreationRequest user) {
    if(Period.between(user.getBirthDate().toLocalDate(), LocalDateTime.now().toLocalDate()).getYears() < minimumAge){
      throw new UnderAgeException("Вік користувача має становити щонайменше " + minimumAge);
    }
    return userRepository.save(userMapper.dtoToEntity(user));
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Користувача не знайдено  за таким ID"));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User updateUser(UserUpdateRequest user) {
    return userRepository.save(userMapper.dtoToEntity(user));
  }

  @Override
  public User partialUpdateUser(PartialUserUpdateRequest user) {
    User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new NoSuchElementException("Користувача не знайдено  за таким ID"));
    return userRepository.save(userMapper.dtoToEntity(userEntity, user));
  }

  @Override
  public List<User> getUsersByBirthDatePeriod(LocalDateTime from, LocalDateTime to) {
    if (from.isAfter(to)) {
      throw new DateViolationException("Дата від не може перевищувати дату до");
    }
    return userRepository.findAllByBirthDateBetween(from, to);
  }
}
