package org.sanedge.application.web.model.request;

import org.sanedge.domain.model.constants.ValidationMessages;
import org.sanedge.domain.model.user.LoginUserInput;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonRootName("user")
@RegisterForReflection
public class LoginRequest {

    @NotBlank(message = ValidationMessages.EMAIL_MUST_BE_NOT_BLANK)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_MUST_BE_NOT_BLANK)
    private String password;

    public LoginUserInput toLoginUserInput() {
        return new LoginUserInput(this.email, this.password);
    }
}