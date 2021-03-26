package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.UserRoles;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserRoles}.
 */
public interface UserRolesService {

    /**
     * Save a userRoles.
     *
     * @param userRoles the entity to save.
     * @return the persisted entity.
     */
    UserRoles save(UserRoles userRoles);

    /**
     * Get all the userRoles.
     *
     * @return the list of entities.
     */
    List<UserRoles> findAll();


    /**
     * Get the "id" userRoles.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserRoles> findOne(Long id);

    /**
     * Delete the "id" userRoles.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
