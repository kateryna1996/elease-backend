package nl.klev.eleasebackend.controllers;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.services.UserService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0

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

            return ResponseEntity.created(uri).body(userDto);
        }
    }

<<<<<<< HEAD

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }
=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
}
