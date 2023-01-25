package nl.klev.eleasebackend.controllers;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.services.UserService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity <Object> addUser(@Valid @RequestBody UserInputDto userInputDto, BindingResult bindingResult) {
        if( bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            UserDto userDto = userService.createUser(userInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/" + userDto.getId()).toUriString());

            return ResponseEntity.created(uri).body("The user with the id " + userDto.getId() + " is successfully created! You can proceed to your account " + uri + " .");
        }
    }


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable("name") String name) {
        List<UserDto> foundUsersDto = userService.getUsersByName(name);

        return ResponseEntity.ok().body(foundUsersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto foundUserDto = userService.getUserById(id);
        return ResponseEntity.ok().body(foundUserDto);
    }
}
