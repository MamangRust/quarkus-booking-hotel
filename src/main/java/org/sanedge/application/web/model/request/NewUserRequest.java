package org.sanedge.application.web.model.request;

import org.sanedge.domain.model.user.CreateUserInput;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("user")
@RegisterForReflection
public class NewUserRequest {
    private String username;
    private String email;
    private String password;

    public CreateUserInput toCreateUserInput() {
        return new CreateUserInput(this.username, this.email, this.password);
    }
}