package org.sanedge.domain.feature;

import java.util.UUID;

import org.sanedge.domain.model.user.User;

public interface FindUserById {
    User handle(UUID id);
}
