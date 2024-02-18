package org.sanedge.infrastructure.web.security.context;

import java.security.Principal;

import jakarta.ws.rs.core.SecurityContext;

public class EmptySecurityContext implements SecurityContext {
    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
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