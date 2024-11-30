package ru.andrew.testapi.repository.repo_service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.repo_model.Role;
import ru.andrew.testapi.model.repo_model.User;
import ru.andrew.testapi.repository.UserRepositorySQL;

@Service
@RequiredArgsConstructor
public class UserServiceSQL implements UserService {
    private final UserRepositorySQL userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }

    public void getAdmin() {
        User user = getCurrentUser();
        user.setRole(Role.ROLE_COMPANY_MEMBER);
        userRepository.save(user);
    }
}
