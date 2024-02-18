package org.sanedge.infrastructure.repository.hibernate.panache;

import java.util.UUID;

import org.sanedge.infrastructure.repository.hibernate.entity.UserEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class AbstractPanacheRepository<ENTITY, ID> implements PanacheRepositoryBase<ENTITY, ID> {
    protected UserEntity findUserEntityById(UUID id) {
        return getEntityManager().find(UserEntity.class, id);
    }
}
