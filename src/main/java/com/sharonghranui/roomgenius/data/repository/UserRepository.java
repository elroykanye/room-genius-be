package com.sharonghranui.roomgenius.data.repository;

import com.sharonghranui.roomgenius.data.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseJpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
