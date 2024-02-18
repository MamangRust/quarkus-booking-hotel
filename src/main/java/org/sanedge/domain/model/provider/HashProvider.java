package org.sanedge.domain.model.provider;

public interface HashProvider {
    String hashPassword(String password);

    boolean checkPassword(String plaintext, String hashed);
}
