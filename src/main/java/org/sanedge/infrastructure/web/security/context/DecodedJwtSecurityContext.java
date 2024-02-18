package org.sanedge.infrastructure.web.security.context;

import java.security.Principal;

import org.sanedge.infrastructure.web.provider.TokenProvider;
import org.sanedge.infrastructure.web.security.profile.Role;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.ws.rs.core.SecurityContext;

public class DecodedJwtSecurityContext implements SecurityContext {
    private final DecodedJWT decodedJWT;
    private final TokenProvider tokenProvider;

    public DecodedJwtSecurityContext(DecodedJWT decodedJWT, TokenProvider tokenProvider) {
        this.decodedJWT = decodedJWT;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Principal getUserPrincipal() {
        return decodedJWT::getSubject;
    }

    @Override
    public boolean isUserInRole(String role) {
        Role[] tokenRoles = tokenProvider.extractRoles(decodedJWT);

        for (Role tokenRole : tokenRoles) {
            if (role.equals(tokenRole.name())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
