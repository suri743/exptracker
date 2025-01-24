package com.suri.exptracker.service;

import com.suri.exptracker.dto.requestdtos.UserDto;
import com.suri.exptracker.dto.UserResponseDto;
import com.suri.exptracker.entity.User;
import com.suri.exptracker.entity.constant.EntityName;
import com.suri.exptracker.exceptions.UserNotFoundException;
import com.suri.exptracker.mapper.UserMapper;
import com.suri.exptracker.mapper.UserResponseMapper;
import com.suri.exptracker.repository.UserRepository;
import com.suri.exptracker.service.validations.ValidationManager;
import com.suri.exptracker.service.validations.ValidationOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ValidationManager<User> validationManager;

    private final UserResponseMapper userResponseMapper;

    public UserResponseDto createUser(UserDto userDto) {

        User user = userMapper.mapDtoToEntity(userDto);

        validationManager.apply(user,null,ValidationOperation.CREATE, EntityName.USER);

        userDto = userMapper.mapEntityToDto(userRepository.save(user));

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
