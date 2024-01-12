package com.pxp.SQLite.demo.controller;

import com.pxp.SQLite.demo.entity.User;
import com.pxp.SQLite.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public User createUser(@RequestBody User user){
        User newUser = userRepository.save(user);
        return  ResponseEntity.ok(newUser).getBody();
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@PathVariable int id,@RequestBody User newUser){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser.setFirstname(newUser.getFirstname());
            updateUser.setLastname(newUser.getLastname());
            updateUser.setDob(newUser.getDob());
            updateUser.setAddress(newUser.getAddress());
            userRepository.save(updateUser);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
