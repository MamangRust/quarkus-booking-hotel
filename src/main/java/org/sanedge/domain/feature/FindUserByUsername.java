package org.sanedge.domain.feature;

import org.sanedge.domain.model.user.User;

public interface FindUserByUsername {
    User handle(String username);
}
