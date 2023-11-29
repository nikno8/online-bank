package mirea.nikit.onlinebank.controller;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
