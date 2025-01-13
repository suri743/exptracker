package com.suri.exptracker.service;

import com.suri.exptracker.dto.UserDto;
import com.suri.exptracker.dto.UserResponseDto;
import com.suri.exptracker.exceptions.UserNotFoundException;
import com.suri.exptracker.mapper.UserMapper;
import com.suri.exptracker.mapper.UserResponseMapper;
import com.suri.exptracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final UserResponseMapper userResponseMapper;

    public UserResponseDto createUser(UserDto userDto) {
        userDto = userMapper.mapEntityToDto(userRepository.save(userMapper.mapDtoToEntity(userDto)));

        return UserResponseDto.builder()
            .id(userDto.getId())
            .name(userDto.getName())
            .mobile(userDto.getMobile())
            .email(userDto.getEmail())
            .build();
    }

    public UserResponseDto getUserById(int id) {

        return userResponseMapper.mapEntityToDto(userRepository
                                                     .findById(id)
                                                     .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.mapEntityListToDtoList(userRepository.findAll());
    }
}
