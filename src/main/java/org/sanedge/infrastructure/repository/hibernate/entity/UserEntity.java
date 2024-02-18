package org.sanedge.infrastructure.repository.hibernate.entity;

import java.util.UUID;

import org.sanedge.domain.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    private UUID id;

    private String username;
    private String bio;
    private String image;
    private String password;
    private String email;

    public UserEntity(User user) {
        this.id = user.getId();
        update(user);
    }

    public void update(User user) {
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}