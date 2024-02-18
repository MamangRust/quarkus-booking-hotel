package org.sanedge.domain.feature;

import org.sanedge.domain.model.user.UpdateUserInput;
import org.sanedge.domain.model.user.User;

public interface UpdateUser {
    User handle(UpdateUserInput updateUserInput);
}
