package nl.klev.eleasebackend.controllers;


import nl.klev.eleasebackend.dtos.UserDto;
import nl.klev.eleasebackend.dtos.UserInputDto;
import nl.klev.eleasebackend.exceptions.BadRequestException;
import nl.klev.eleasebackend.services.UserService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//adding filter?
    @GetMapping("")
    public ResponseEntity<Object> getUsers(@RequestParam Map<String, String> params) {
       List<UserDto> response = new ArrayList<>();

        if(params.isEmpty()) {
            List<UserDto> userDtos = userService.getUsers();

            response = userDtos;
        }
        if(params.size() > 1) {
            throw new BadRequestException("Only one request parameter at a time is allowed! ");
        }
        if(params.containsKey("username")){
            String stringValue = params.get("username");
           UserDto foundUsersDto = userService.getUserByUsername(stringValue);

            response.add(foundUsersDto);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<List<UserDto>> getUsersByName(@PathVariable("name") String name) {
        List<UserDto> foundUsersDto = userService.getUsersByUsername(name);

        return ResponseEntity.ok().body(foundUsersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto foundUserDto = userService.getUserById(id);
        return ResponseEntity.ok().body(foundUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity updateUser(@PathVariable("name") String username,@Valid @RequestBody UserInputDto userInputDto){
        userService.updateUserInformation(username,userInputDto);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{name}")
    public ResponseEntity deleteUserByName(@PathVariable("name") String name) {
       userService.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }

}
