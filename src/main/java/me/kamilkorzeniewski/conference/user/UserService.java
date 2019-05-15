package me.kamilkorzeniewski.conference.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}
