package org.sanedge.infrastructure.provider;

import org.mindrot.jbcrypt.BCrypt;
import org.sanedge.domain.model.provider.HashProvider;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BCryptHashProvider implements HashProvider {
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String plainText, String hashed) {
        return BCrypt.checkpw(plainText, hashed);
    }
}
