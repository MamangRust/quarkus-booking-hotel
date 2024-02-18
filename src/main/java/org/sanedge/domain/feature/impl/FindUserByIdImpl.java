package org.sanedge.domain.feature.impl;

import java.util.UUID;

import org.sanedge.domain.exception.UserNotFoundException;
import org.sanedge.domain.feature.FindUserById;
import org.sanedge.domain.model.user.User;
import org.sanedge.domain.model.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindUserByIdImpl implements FindUserById {
    private final UserRepository userRepository;

    @Override
    public User handle(UUID id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }
}
