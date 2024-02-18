package org.sanedge.domain.feature.impl;

import org.sanedge.domain.exception.EmailAlreadyExistsException;
import org.sanedge.domain.exception.UsernameAlreadyExistsException;
import org.sanedge.domain.feature.CreateUser;
import org.sanedge.domain.model.provider.HashProvider;
import org.sanedge.domain.model.user.CreateUserInput;
import org.sanedge.domain.model.user.User;
import org.sanedge.domain.model.user.UserModelBuilder;
import org.sanedge.domain.model.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserImpl implements CreateUser {
    private final UserRepository userRepository;
    private final HashProvider hashProvider;
    private final UserModelBuilder userBuilder;

    @Override
    public User handle(CreateUserInput createUserInput) {
        final var user = userBuilder.build(
                createUserInput.getUsername(),
                createUserInput.getEmail(),
                createUserInput.getPassword());
        checkExistingUsername(user.getUsername());
        checkExistingEmail(user.getEmail());
        user.setPassword(hashProvider.hashPassword(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    private void checkExistingUsername(String username) {
        if (userRepository.existsBy("username", username)) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private void checkExistingEmail(String email) {
        if (userRepository.existsBy("email", email)) {
            throw new EmailAlreadyExistsException();
        }
    }
}
