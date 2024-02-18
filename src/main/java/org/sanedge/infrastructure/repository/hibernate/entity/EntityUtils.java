package org.sanedge.infrastructure.repository.hibernate.entity;

import org.sanedge.domain.model.user.User;
import org.sanedge.domain.model.user.UserModelBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class EntityUtils {
    private final UserModelBuilder userBuilder;

    public User user(UserEntity userEntity) {
        final var id = userEntity.getId();
        final var username = userEntity.getUsername();
        final var bio = userEntity.getBio();
        final var image = userEntity.getImage();
        final var password = userEntity.getPassword();
        final var email = userEntity.getEmail();
        return userBuilder.build(id, username, bio, image, password, email);
    }
}
