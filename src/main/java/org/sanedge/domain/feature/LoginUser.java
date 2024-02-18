package org.sanedge.domain.feature;

import org.sanedge.domain.model.user.LoginUserInput;
import org.sanedge.domain.model.user.User;

public interface LoginUser {
    User handle(LoginUserInput loginUserInput);
}
