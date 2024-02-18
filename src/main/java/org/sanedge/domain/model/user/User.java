package org.sanedge.domain.model.user;

import java.util.UUID;

import org.sanedge.domain.model.constants.ValidationMessages;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @NotNull
    private UUID id;

    @NotBlank(message = ValidationMessages.USERNAME_MUST_BE_NOT_BLANK)
    private String username;

    @Email
    @NotBlank(message = ValidationMessages.EMAIL_MUST_BE_NOT_BLANK)
    private String email;

    @NotBlank(message = ValidationMessages.PASSWORD_MUST_BE_NOT_BLANK)
    private String password;

    private String bio;
    private String image;
}
