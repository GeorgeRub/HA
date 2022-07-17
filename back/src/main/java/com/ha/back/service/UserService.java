package com.ha.back.service;

import com.ha.back.exceptions.account.NotFoundException;
import com.ha.back.models.user.User;
import com.ha.back.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByName(String name) throws NotFoundException {
        Optional<User> user = userRepository.findByUsername(name);
        if (!user.isPresent()) {
            throw new NotFoundException("We could not find any user with name " + name);
        }
        return user.get();
    }
}
