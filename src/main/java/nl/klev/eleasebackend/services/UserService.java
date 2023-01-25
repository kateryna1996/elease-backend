package nl.klev.eleasebackend.services;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.repositories.UserRepository;
import nl.klev.eleasebackend.utilities.UserTransform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDto createUser(UserInputDto userInputDto){
        User createdUser = UserTransform.toUser(userInputDto);

        userRepository.save(createdUser);

        return UserTransform.toUserDto(createdUser);
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtosList = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for( User user : usersList) {
            userDtosList.add(UserTransform.toUserDto(user));
        }
        return userDtosList;
    }
}
