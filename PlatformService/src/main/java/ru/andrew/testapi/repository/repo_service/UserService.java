package ru.andrew.testapi.repository.repo_service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.andrew.testapi.model.repo_model.User;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);
    User getCurrentUser();
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void getAdmin();
}
