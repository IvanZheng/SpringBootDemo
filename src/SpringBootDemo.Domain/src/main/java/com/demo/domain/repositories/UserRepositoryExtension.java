package com.demo.domain.repositories;

import com.demo.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserRepositoryExtension {
    List<User> findByProfileAddress(String address);
}
