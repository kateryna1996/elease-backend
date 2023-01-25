package nl.klev.eleasebackend.utilities;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.models.User;

public class UserTransform {

    public static User toUser(UserInputDto userInputDto) {
        var user = new User();

        user.setFullName(userInputDto.getFullName());
        user.setDob(userInputDto.getDob());
        user.setEmail(userInputDto.getEmail());
        user.setIban(userInputDto.getIban());
        user.setPassword(userInputDto.getPassword());
        user.setUsername(userInputDto.getUsername());
        user.setDrivingLicenseNumber(userInputDto.getDrivingLicenseNumber());

        return user;
    }

    public static UserDto toUserDto(User user) {
        var userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setDob(user.getDob());
        userDto.setEmail(user.getEmail());
        userDto.setIban(user.getIban());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setDrivingLicenseNumber(user.getDrivingLicenseNumber());

        return userDto;
    }
}
