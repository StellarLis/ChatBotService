package ru.andrew.testapi.repository.repo_service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.UserSQL;
import ru.andrew.testapi.model.service_model.ServiceUser;
import ru.andrew.testapi.repository.UserRepositorySQL;

@Service
@RequiredArgsConstructor
public class UserServiceSQL implements UserService {
    private final UserRepositorySQL userRepository;

    public DatabaseUser getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public DatabaseUser getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }

    @Override
    public DatabaseUser save(ServiceUser user) {
        UserSQL dbUser = UserSQL.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole()).build();
        return userRepository.save(dbUser);
    }

    public void update(ServiceUser user) {
        UserSQL dbUser = toDbUser(user);
        userRepository.save(dbUser);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private UserSQL toDbUser(ServiceUser serviceUser) {
        UserSQL dbUser = UserSQL.builder()
                .id(serviceUser.getId())
                .username(serviceUser.getUsername())
                .password(serviceUser.getPassword())
                .email(serviceUser.getEmail())
                .role(serviceUser.getRole())
                .companyName(serviceUser.getCompanyName()).build();
        dbUser.setDocuments(serviceUser.getDocuments());
        return dbUser;
    }
}
