package com.example.cbox.mapper;

import com.example.cbox.config.MapperConfiguration;
import com.example.cbox.dto.create.FileCreateEditDto;
import com.example.cbox.dto.read.FileReadDto;
import com.example.cbox.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface FileMapper {

    FileReadDto toFileReadDto(FileEntity file);

    FileEntity toFileEntity(FileCreateEditDto fileReadDto);

    void map(@MappingTarget FileEntity to, FileCreateEditDto from);
}
