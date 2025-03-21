package com.workintech.twitter.repository;

import com.workintech.twitter.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    @Query("SELECT u FROM UserSecurity u WHERE u.email = :email")
    Optional<UserSecurity> findByEmail(@Param("email") String email);
}
