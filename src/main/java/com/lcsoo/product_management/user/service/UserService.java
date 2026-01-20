package com.lcsoo.product_management.user.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.lcsoo.product_management.user.domain.User;
import com.lcsoo.product_management.user.dto.AddUserRequest;
import com.lcsoo.product_management.user.dto.AddUserResponse;
import com.lcsoo.product_management.user.dto.DeleteUserRequest;
import com.lcsoo.product_management.user.dto.UpdateUserRequest;
import com.lcsoo.product_management.user.dto.UserResponse;
import com.lcsoo.product_management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AddUserResponse addUser(AddUserRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(request.password())
                .name(request.name())
                .address(request.address())
                .build();

        User savedUser = userRepository.save(user);

        return new AddUserResponse(savedUser);
    }

    public UserResponse userInfo(String username) {
        User user = userRepository.findByName(username).get();

        UserResponse response = new UserResponse(Map.of(
            "email", user.getEmail(),
            "name", user.getName(),
            "address", user.getAddress().toMap()
        ));

        return response;
    }

    public UserResponse updateUser(UpdateUserRequest request) {
        User user = userRepository.findByName(request.name()).get();

        user.update(request.email(), request.password(), request.name(), request.address());

        User savedUser = userRepository.save(user);
        
        return new UserResponse(Map.of(
            "email", savedUser.getEmail(),
            "name", savedUser.getName(),
            "address", savedUser.getAddress().toMap(),
            "message", "유저 정보 수정 완료"
        ));
    }

    public UserResponse deleteUser(DeleteUserRequest request) {
        User user = userRepository.findByName(request.name()).get();

        if (user == null) {
            throw new IllegalArgumentException("유저 정보 삭제 실패 - 유저 정보가 없습니다.");
        } 

        userRepository.delete(user);

        return new UserResponse(Map.of(
            "message", "유저 정보 삭제 완료",
            "name", user.getName(),
            "user_id", user.getId()
        ));
    }
}
