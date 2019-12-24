package com.demo.domain.repositories;

import com.demo.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>,
        JpaSpecificationExecutor<User>,
        UserRepositoryExtension {
    List<User> findByName(String name);
}
