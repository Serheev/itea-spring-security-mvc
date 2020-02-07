package com.serheev.repository;

import com.serheev.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query( "SELECT u FROM UserEntity u WHERE u.id = :id")
    UserEntity findUserEntityById(@Param("id") Long id);

    @Query( "SELECT u FROM UserEntity u WHERE u.login = :login")
    UserEntity findUserEntityByLogin(@Param("login") String login);

}
