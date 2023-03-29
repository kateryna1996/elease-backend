package nl.klev.eleasebackend.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String name){
        super("The user with the name " + name + " cannot be found!");
    }
}
