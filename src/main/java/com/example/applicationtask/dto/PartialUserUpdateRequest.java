package com.example.applicationtask.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartialUserUpdateRequest {
  @NotNull
  private Long id;
  @Email
  private String email;
  private String firstName;
  private String lastName;
  @Past
  private LocalDateTime birthDate;
  private String address;
  private String phoneNumber;
}