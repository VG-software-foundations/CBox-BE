package com.example.cbox.mapper;

import com.example.cbox.config.MapperConfigurationn;
import com.example.cbox.dto.create.UserCreateEditDto;
import com.example.cbox.dto.read.UserReadDto;
import com.example.cbox.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfigurationn.class)
public interface UserMapper {
    UserReadDto toUserReadDto(User user);

    User toUser(UserCreateEditDto dto);

    void map(@MappingTarget User to, UserCreateEditDto from);
}
