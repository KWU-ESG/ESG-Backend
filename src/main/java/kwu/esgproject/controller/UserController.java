package kwu.esgproject.controller;

import kwu.esgproject.domain.*;
import kwu.esgproject.dto.User.*;
import kwu.esgproject.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



}

