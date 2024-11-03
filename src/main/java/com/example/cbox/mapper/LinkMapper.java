package com.example.cbox.mapper;

import com.example.cbox.config.MapperConfiguration;
import com.example.cbox.dto.create.LinkCreateEditDto;
import com.example.cbox.dto.read.LinkReadDto;
import com.example.cbox.entity.LinkRestrictionBypass;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface LinkMapper {

    LinkReadDto toLinkReadDto(LinkRestrictionBypass file);

    LinkRestrictionBypass toLink(LinkCreateEditDto linkCreateEditDto);

    void map(@MappingTarget LinkRestrictionBypass to, LinkCreateEditDto from);
}
