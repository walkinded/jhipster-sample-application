package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.UserRoles;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserRoles entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}
