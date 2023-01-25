package nl.klev.eleasebackend.services;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.repositories.UserRepository;
import nl.klev.eleasebackend.utilities.UserTransform;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
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
<<<<<<< HEAD

    public List<UserDto> getUsers() {
        List<UserDto> userDtosList = new ArrayList<>();
        List<User> usersList = userRepository.findAll();
        for( User user : usersList) {
            userDtosList.add(UserTransform.toUserDto(user));
        }
        return userDtosList;
    }
=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
}
