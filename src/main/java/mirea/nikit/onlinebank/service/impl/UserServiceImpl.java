package mirea.nikit.onlinebank.service.impl;

import mirea.nikit.onlinebank.model.Account;
import mirea.nikit.onlinebank.model.User;
import mirea.nikit.onlinebank.repository.UserRepository;
import mirea.nikit.onlinebank.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public List<User> getAllAccounts() {
        return userRepository.findAll();
    }
}
