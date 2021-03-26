package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.UserRoles;
import com.mycompany.myapp.service.UserRolesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.UserRoles}.
 */
@RestController
@RequestMapping("/api")
public class UserRolesResource {

    private final Logger log = LoggerFactory.getLogger(UserRolesResource.class);

    private static final String ENTITY_NAME = "userRoles";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserRolesService userRolesService;

    public UserRolesResource(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    /**
     * {@code POST  /user-roles} : Create a new userRoles.
     *
     * @param userRoles the userRoles to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userRoles, or with status {@code 400 (Bad Request)} if the userRoles has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-roles")
    public ResponseEntity<UserRoles> createUserRoles(@RequestBody UserRoles userRoles) throws URISyntaxException {
        log.debug("REST request to save UserRoles : {}", userRoles);
        if (userRoles.getId() != null) {
            throw new BadRequestAlertException("A new userRoles cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserRoles result = userRolesService.save(userRoles);
        return ResponseEntity.created(new URI("/api/user-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-roles} : Updates an existing userRoles.
     *
     * @param userRoles the userRoles to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userRoles,
     * or with status {@code 400 (Bad Request)} if the userRoles is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userRoles couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-roles")
    public ResponseEntity<UserRoles> updateUserRoles(@RequestBody UserRoles userRoles) throws URISyntaxException {
        log.debug("REST request to update UserRoles : {}", userRoles);
        if (userRoles.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserRoles result = userRolesService.save(userRoles);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userRoles.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-roles} : get all the userRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userRoles in body.
     */
    @GetMapping("/user-roles")
    public List<UserRoles> getAllUserRoles() {
        log.debug("REST request to get all UserRoles");
        return userRolesService.findAll();
    }

    /**
     * {@code GET  /user-roles/:id} : get the "id" userRoles.
     *
     * @param id the id of the userRoles to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userRoles, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-roles/{id}")
    public ResponseEntity<UserRoles> getUserRoles(@PathVariable Long id) {
        log.debug("REST request to get UserRoles : {}", id);
        Optional<UserRoles> userRoles = userRolesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userRoles);
    }

    /**
     * {@code DELETE  /user-roles/:id} : delete the "id" userRoles.
     *
     * @param id the id of the userRoles to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-roles/{id}")
    public ResponseEntity<Void> deleteUserRoles(@PathVariable Long id) {
        log.debug("REST request to delete UserRoles : {}", id);
        userRolesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
