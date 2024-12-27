package ru.andrew.testapi.repository.repo_service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.service_model.ServiceUser;

public interface UserService extends UserDetailsService {
    DatabaseUser getByUsername(String username);
    DatabaseUser getCurrentUser();
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    DatabaseUser save(ServiceUser user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void update(ServiceUser user);
}
