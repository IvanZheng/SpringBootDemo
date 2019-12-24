package com.demo.domain.repositories;

import com.demo.domain.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryExtensionImpl implements UserRepositoryExtension {

    private EntityManager entityManager;

    public UserRepositoryExtensionImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findByProfileAddress(String address) {
        return null;
    }
}
