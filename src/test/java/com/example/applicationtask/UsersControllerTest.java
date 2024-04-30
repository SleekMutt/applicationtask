package com.example.applicationtask;


import com.example.applicationtask.controller.Users;
import com.example.applicationtask.entity.User;
import com.example.applicationtask.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Users.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UsersControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;



  @Test
  void testGetUserById() throws Exception {
    Long userId = 1L;
    User user = new User();
    user.setId(userId);
    user.setFirstName("test");
    when(userService.getUserById(userId)).thenReturn(user);

    mockMvc.perform(get("/user/{id}", userId)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.firstName").value("test"));
  }

  @Test
  void testGetAllUsers() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setFirstName("test1");

    User user2 = new User();
    user2.setId(2L);
    user2.setFirstName("test2");

    when(userService.getAllUsers()).thenReturn(List.of(user, user2));

    mockMvc.perform(get("/user/")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].firstName").value("test1"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].firstName").value("test2"))
    ;
  }

  @Test
  void testSearchAllUsersByBirthdatePeriod() throws Exception {
    LocalDateTime from = LocalDateTime.of(1990, 1, 1, 0, 0);
    LocalDateTime to = LocalDateTime.of(1990, 12, 31, 23, 59);

    User user1 = new User();
    user1.setId(1L);
    user1.setFirstName("test");
    user1.setLastName("test");
    user1.setBirthDate(LocalDateTime.of(1990, 5, 15, 0, 0));

    User user2 = new User();
    user2.setId(2L);
    user2.setFirstName("test1");
    user2.setLastName("test1");
    user2.setBirthDate(LocalDateTime.of(1990, 8, 20, 0, 0));

    List<User> userList = List.of(user1, user2);

    when(userService.getUsersByBirthDatePeriod(from, to)).thenReturn(userList);

    mockMvc.perform(get("/user/search-all-by-birthdate-period")
                    .param("from", from.toString())
                    .param("to", to.toString())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].firstName").value("test"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].firstName").value("test1"))
            .andExpect(jsonPath("$.length()").value(2));
  }
}
