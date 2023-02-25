package nl.klev.eleasebackend.services;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.exceptions.UserNotFoundException;
import nl.klev.eleasebackend.models.Authority;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.repositories.UserRepository;
import nl.klev.eleasebackend.utilities.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(UserInputDto userInputDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);

        userInputDto.setApikey(randomString);
        userInputDto.setEnabled(true);
        userInputDto.setPassword(encoder.encode(userInputDto.getPassword()));

        User createdUser = toUser(userInputDto);

        if (userWithEmailExists(createdUser.getEmail())) {
            throw new RecordNotFoundException("The user with this email already exists. You may have only one account!");
        } else {
            userRepository.save(createdUser);
            return createdUser.getUsername();
        }
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for (User user : usersList) {
            userDtoList.add(toUserDto(user));
        }
        if (usersList.isEmpty()) {
            throw new RecordNotFoundException("The list is empty");
        }
        return userDtoList;
    }

    public List<UserDto> getUsersByUsernameIncludingString(String string) {
        List<UserDto> dto = new ArrayList<>();
        List<User> users = userRepository.findAllByUsernameContaining(string);
        if (users.isEmpty()) {
            throw new UserNotFoundException(string);
        } else {
            for (User u : users) {
                dto.add(toUserDto(u));
            }
        }
        return dto;
    }

    public UserDto getUser(String username) {
        UserDto userDto = new UserDto();
        Optional<User> foundUser = userRepository.findById(username);
        if (foundUser.isPresent()) {
            userDto = toUserDto(foundUser.get());
        } else {
            throw new RecordNotFoundException("The user " + username + " cannot be found!");
        }
        return userDto;
    }

    public void updateUserInformation(String username, UserInputDto newUser) {
        User user = userRepository.findById(username).get();
        if (userExists(username)) {
            user.setPassword(encoder.encode(newUser.getPassword()));
            if (newUser.getEnabled() != null) {
                user.setEnabled(newUser.getEnabled());
            }
//            check?
            if(newUser.getApikey() != null) {
                user.setApikey(newUser.getApikey());
            } else {
                user.setApikey(user.getApikey());
            }
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public void deleteUser(String username) {
        if(userExists(username)) {
            userRepository.deleteById(username);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = toUserDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }


//    -----------  Extra ----------------

    public boolean userWithEmailExists(String email) {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findByEmail(email));
        return foundUser.isPresent();
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public User toUser(UserInputDto userInputDto) {
        var user = new User();

        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        user.setUsername(userInputDto.getUsername());
        user.setApikey(userInputDto.getApikey());
        user.setEnabled(userInputDto.getEnabled());

        return user;
    }

    public UserDto toUserDto(User user) {
        var userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.email = user.getEmail();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }
}
