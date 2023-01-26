package nl.klev.eleasebackend.utilities;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.models.User;

public class UserTransform {

    public static User toUser(UserInputDto userInputDto) {
        var user = new User();

        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        user.setUsername(userInputDto.getUsername());

        return user;
    }

    public static UserDto toUserDto(User user) {
        var userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
