package org.sanedge.domain.feature;

import org.sanedge.domain.model.user.CreateUserInput;
import org.sanedge.domain.model.user.User;

public interface CreateUser {
    User handle(CreateUserInput createUserInput);
}
