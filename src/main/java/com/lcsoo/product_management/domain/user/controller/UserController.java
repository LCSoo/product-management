package com.lcsoo.product_management.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lcsoo.product_management.domain.user.dto.AddUserRequest;
import com.lcsoo.product_management.domain.user.dto.AddUserResponse;
import com.lcsoo.product_management.domain.user.dto.DeleteUserRequest;
import com.lcsoo.product_management.domain.user.dto.UpdateUserRequest;
import com.lcsoo.product_management.domain.user.dto.UserResponse;
import com.lcsoo.product_management.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AddUserRequest request) {
        AddUserResponse response = userService.addUser(request);
        return new ResponseEntity<>(response.user(), HttpStatus.CREATED);
    }
    
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestParam("name") String username) {
        UserResponse response = userService.userInfo(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserRequest request) {
        UserResponse response = userService.deleteUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
