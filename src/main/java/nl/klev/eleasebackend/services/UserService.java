package nl.klev.eleasebackend.services;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.exceptions.UserNotFoundException;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.repositories.UserRepository;
import nl.klev.eleasebackend.utilities.UserTransform;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserInputDto userInputDto) {
        UserDto createdUserDto = new UserDto();
        User createdUser = UserTransform.toUser(userInputDto);
        if (userExists(createdUser.getEmail())) {
            throw new RecordNotFoundException("The user with this email already exists. You may have only one account!");
        } else {
//            to see the result , need to delete later
            userRepository.save(createdUser);
            createdUserDto = UserTransform.toUserDto(createdUser);
            return createdUserDto;
        }
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtosList = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for (User user : usersList) {
            userDtosList.add(UserTransform.toUserDto(user));
        }
        if (usersList.isEmpty()) {
            throw new RecordNotFoundException("The list is empty");
        }
        return userDtosList;
    }

    public UserDto getUserByUsername(String name) {
        UserDto dto = new UserDto();
        User user = userRepository.findByUsername(name);
        dto = UserTransform.toUserDto(user);
        return dto;
    }

    public List<UserDto> getUsersByUsernameIncludingString(String string) {
        List<UserDto> dto = new ArrayList<>();
        List<User> users = userRepository.findAllByUsernameContaining(string);
        if (users.isEmpty()) {
            throw new UserNotFoundException(string);
        } else {
            for (User u : users) {
                dto.add(UserTransform.toUserDto(u));
            }
        }
        return dto;
    }

    public UserDto getUserById(Long id) {
        UserDto userDto = new UserDto();
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            userDto = UserTransform.toUserDto(foundUser.get());
        } else {
            throw new RecordNotFoundException("The user with the id " + id + " cannot be found!");
        }
        return userDto;
    }

    //    ?partial fields change
    public void updateUserInformation(String username, UserInputDto newUserDto) {
        User foundUser = userRepository.findByUsername(username);
        if (foundUser == null) {
            throw new UserNotFoundException(username);
        } else {
            User updatedUser = UserTransform.toUser(newUserDto);
            updatedUser.setId(foundUser.getId());
            foundUser = updatedUser;
            userRepository.save(foundUser);
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByName(String name) {
        User foundUser = userRepository.findByUsername(name);
        userRepository.delete(foundUser);
    }

    //********************** Extra  **************************
    public boolean userExists(String email) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByEmail(email));
        return foundUser.isPresent();
    }

}
