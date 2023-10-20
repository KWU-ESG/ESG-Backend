package kwu.esgproject.controller;

import kwu.esgproject.domain.*;
import kwu.esgproject.dto.User.*;
import kwu.esgproject.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join/user")
    public String joinUser(@RequestBody @Valid CreateUserRequest request){
        Long userId = userService.joinUser(request.getName(), request.getNickname(), request.getBirth(), request.getEmail(), request.getPassword(), request.getInterest());
        User findUser = userService.findById(userId);

        return findUser.toString();
    }
    @PostMapping("/join/admin")
    public String joinAdmin(@RequestBody @Valid CreateUserRequest request){
        Long userId = userService.joinAdmin(request.getName(), request.getNickname(), request.getBirth(), request.getEmail(), request.getPassword());
        User findUser = userService.findById(userId);

        return findUser.toString();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        return userService.login(request);
    }

}

