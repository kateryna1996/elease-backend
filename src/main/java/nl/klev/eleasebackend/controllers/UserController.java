package nl.klev.eleasebackend.controllers;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.exceptions.BadRequestException;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.services.UserService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            String password = userInputDto.getPassword();

            String username = userService.createUser(userInputDto);
            userService.addAuthority(username, "ROLE_USER");

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                    .buildAndExpand(username).toUri();

            return ResponseEntity.created(uri).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("username") String username) {
        UserDto foundUserDto = userService.getUser(username);
        return ResponseEntity.ok().body(foundUserDto);
    }

    @GetMapping("/all/{string}")
    public ResponseEntity<List<UserDto>> getUsersByNameContainingString(@PathVariable("string") String name) {
        List<UserDto> foundUsersDto = userService.getUsersByUsernameIncludingString(name);

        return ResponseEntity.ok().body(foundUsersDto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @Valid @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            userService.updateUserInformation(username, userInputDto);
            return ResponseEntity.ok().body("The user is updated!");
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping("/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        if(userService.getAuthorities(authority) != null) {
            userService.removeAuthority(username, authority);
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("The specified authority of this user was not found!");
        }

    }
}
