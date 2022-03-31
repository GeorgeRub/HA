package com.ha.back.service;

import com.ha.back.exceptions.NotFountAnyUser;
import com.ha.back.models.user.User;
import com.ha.back.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getByName(String name) throws NotFountAnyUser {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isEmpty()) {
            throw new NotFountAnyUser("We could not find any user with name " + name);
        }
        return user.get();
    }
}
