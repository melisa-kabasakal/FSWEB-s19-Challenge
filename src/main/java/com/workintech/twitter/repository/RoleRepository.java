package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r From Role r WHERE r.authority = :authority")
    Optional<Role> findByAuthority(@Param("authority") String authority);
}
