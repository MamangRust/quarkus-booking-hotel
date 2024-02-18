package org.sanedge.domain.feature.impl;

import org.sanedge.domain.exception.UserNotFoundException;
import org.sanedge.domain.feature.FindUserByUsername;
import org.sanedge.domain.model.user.User;
import org.sanedge.domain.model.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindUserByUsernameImpl implements FindUserByUsername {
    private final UserRepository userRepository;

    @Override
    public User handle(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
}
