package org.sanedge.domain.feature.impl;

import org.sanedge.domain.exception.InvalidPasswordException;
import org.sanedge.domain.exception.UserNotFoundException;
import org.sanedge.domain.feature.LoginUser;
import org.sanedge.domain.model.provider.HashProvider;
import org.sanedge.domain.model.user.LoginUserInput;
import org.sanedge.domain.model.user.User;
import org.sanedge.domain.model.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUserImpl implements LoginUser {
    private final UserRepository userRepository;
    private final HashProvider hashProvider;

    @Override
    public User handle(LoginUserInput loginUserInput) {
        final var user = userRepository
                .findByEmail(loginUserInput.getEmail())
                .orElseThrow(UserNotFoundException::new);
        if (!isPasswordValid(loginUserInput.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }

    private boolean isPasswordValid(String password, String hashedPassword) {
        return hashProvider.checkPassword(password, hashedPassword);
    }
}