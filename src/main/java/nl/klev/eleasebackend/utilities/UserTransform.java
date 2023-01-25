package nl.klev.eleasebackend.utilities;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.models.User;

public class UserTransform {

    public static User toUser(UserInputDto userInputDto) {
        var user = new User();

<<<<<<< HEAD
        user.setFullName(userInputDto.getFullName());
        user.setDob(userInputDto.getDob());
        user.setEmail(userInputDto.getEmail());
        user.setIban(userInputDto.getIban());
        user.setPassword(userInputDto.getPassword());
        user.setUsername(userInputDto.getUsername());
        user.setDrivingLicenseNumber(userInputDto.getDrivingLicenseNumber());
=======
        user.setFirstName(userInputDto.getFirstName());
        user.setLastName(userInputDto.getLastName());
        user.setDob(userInputDto.getDob());
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0

        return user;
    }

    public static UserDto toUserDto(User user) {
<<<<<<< HEAD
        var userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setDob(user.getDob());
        userDto.setEmail(user.getEmail());
        userDto.setIban(user.getIban());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setDrivingLicenseNumber(user.getDrivingLicenseNumber());
=======
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setDob(user.getDob());
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0

        return userDto;
    }
}
