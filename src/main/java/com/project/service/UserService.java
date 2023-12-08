package com.project.service;
import com.project.payload.request.user.UserRequest;
import com.project.payload.response.ResponseMessage;
import com.project.payload.response.UserResponse;
import com.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseMessage<UserResponse> saveUser(UserRequest userRequest, String userRole) {
    }
}