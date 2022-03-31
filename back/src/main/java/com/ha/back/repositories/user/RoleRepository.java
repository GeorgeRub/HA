package com.ha.back.repositories.user;

import java.util.Optional;

import com.ha.back.models.user.ERole;
import com.ha.back.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
