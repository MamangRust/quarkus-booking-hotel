package org.sanedge.application.web.model.response;

import org.sanedge.domain.model.user.User;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName("user")
@RegisterForReflection
public class UserResponse {
    private String username;
    private String bio;
    private String image;
    private String email;
    private String token;

    public UserResponse(User user, String token) {
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.image = user.getImage();
        this.email = user.getEmail();
        this.token = token;
    }
}
