package org.sanedge.infrastructure.web.provider;

import org.sanedge.infrastructure.web.security.profile.Role;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenProvider {

    String createUserToken(String subject);

    DecodedJWT verify(String token);

    Role[] extractRoles(DecodedJWT decodedJWT);
}
