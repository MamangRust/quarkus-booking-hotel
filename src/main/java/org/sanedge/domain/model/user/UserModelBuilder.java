package org.sanedge.domain.model.user;

import java.util.UUID;

import org.sanedge.domain.validator.ModelValidator;

import jakarta.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class UserModelBuilder {
    private final ModelValidator modelValidator;

    public User build(String username, String email, String password) {
        return modelValidator.validate(
                new User(UUID.randomUUID(), username, email, password, null, null));
    }

    public User build(UUID id, String username, String bio, String image, String password, String email) {
        return modelValidator.validate(
                new User(id, username, email, password, bio, image));
    }
}
