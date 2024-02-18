package org.sanedge.domain.model.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserInput {
    private UUID id;
    private String email;
    private String username;
    private String bio;
    private String image;
}
