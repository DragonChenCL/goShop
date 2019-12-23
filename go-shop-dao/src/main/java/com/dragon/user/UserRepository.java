package com.dragon.user;

import com.dragon.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByDeleteIsFalseAndAvailableIsTrueAndAccount(String account);

    Optional<UserEntity> findByDeleteIsFalseAndAvailableIsTrueAndId(String id);
}
