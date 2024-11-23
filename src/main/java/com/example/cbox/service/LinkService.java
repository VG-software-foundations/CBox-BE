//package com.example.cbox.service;
//
//import com.example.cbox.dto.create.LinkCreateEditDto;
//import com.example.cbox.dto.read.LinkReadDto;
//import com.example.cbox.mapper.LinkMapper;
//import com.example.cbox.repository.LinkRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class LinkService {
//    private final LinkRepository linkRepository;
//    private final LinkMapper linkMapper;
//
//    public Page<LinkReadDto> findAll(Integer page, Integer limit) {
//        PageRequest req = PageRequest.of(page - 1, limit);
//        return linkRepository.findAll(req)
//                .map(linkMapper::toLinkReadDto);
//    }
//
//    public Optional<LinkReadDto> findById(Long id) {
//        return linkRepository.findById(id)
//                .map(linkMapper::toLinkReadDto);
//    }
//
//    @Transactional
//    public LinkReadDto create(LinkCreateEditDto dto) {
//        return Optional.of(dto)
//                .map(linkMapper::toLink)
//                .map(linkRepository::save)
//                .map(linkMapper::toLinkReadDto)
//                .orElseThrow();
//    }
//
//    @Transactional
//    public Optional<LinkReadDto> update(Long id, LinkCreateEditDto dto) {
//        return linkRepository.findById(id)
//                .map(user -> {
//                    linkMapper.map(user, dto);
//                    return user;
//                })
//                .map(linkRepository::saveAndFlush)
//                .map(linkMapper::toLinkReadDto);
//    }
//
//    @Transactional
//    public boolean delete(Long id) {
//        return linkRepository.findById(id).isPresent();
//    }
//}
