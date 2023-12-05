package mirea.nikit.onlinebank.controller;

import lombok.RequiredArgsConstructor;
import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.model.User;
import mirea.nikit.onlinebank.service.UserService;
import mirea.nikit.onlinebank.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    @GetMapping
    public List<User> getAllUsers() {
        return userServiceImpl.getAllAccounts();
    }
}
