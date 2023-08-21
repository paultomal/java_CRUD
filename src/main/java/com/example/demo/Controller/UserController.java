package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class UserController {
    private final UserServices services;

    public UserController(UserServices services) {
        this.services = services;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDTO dto, BindingResult result) {

        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError fieldError) {
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        UserDTO dto1 = UserDTO.form(services.save(dto));
        return new ResponseEntity<>(dto1, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        UserDTO dto = UserDTO.form(services.getUserById(id));
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO dto, @PathVariable Long id){
        UserDTO dto1 = UserDTO.form(services.updateUser(id,dto));
        return new ResponseEntity<>(dto1,HttpStatus.OK);
        //return new ResponseEntity<>(services.updateUser(id,dto),HttpStatus.OK);

    }
    @GetMapping("/listofuser")
    public ResponseEntity<?> getAllUser(){
        List<User> users = services.getAllUser();
        List<UserDTO> dtoList = users.stream()
                .map(UserDTO::form)
                .toList();
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
    @GetMapping("/asd")
    public ResponseEntity<?> getUserByAge(){
        List<User>  users = services.getAllUser();
        List<UserDTO> userList = users.stream()
                .filter(user -> user.getAge()<20)
                .map(UserDTO::form)
                .toList();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }
}
