package com.example.applicationtask.mapper;

import com.example.applicationtask.dto.PartialUserUpdateRequest;
import com.example.applicationtask.dto.UserCreationRequest;
import com.example.applicationtask.dto.UserUpdateRequest;
import com.example.applicationtask.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User dtoToEntity(UserCreationRequest dto);
  User dtoToEntity(UserUpdateRequest dto);
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User dtoToEntity(@MappingTarget User user, PartialUserUpdateRequest dto);
}
