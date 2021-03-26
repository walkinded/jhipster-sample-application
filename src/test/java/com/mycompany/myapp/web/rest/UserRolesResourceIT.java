package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestAppJHipsterApp;
import com.mycompany.myapp.domain.UserRoles;
import com.mycompany.myapp.repository.UserRolesRepository;
import com.mycompany.myapp.service.UserRolesService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserRolesResource} REST controller.
 */
@SpringBootTest(classes = TestAppJHipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserRolesResourceIT {

    private static final Long DEFAULT_ID_USER = 1L;
    private static final Long UPDATED_ID_USER = 2L;

    private static final Long DEFAULT_ID_ROLE = 1L;
    private static final Long UPDATED_ID_ROLE = 2L;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private UserRolesService userRolesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserRolesMockMvc;

    private UserRoles userRoles;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRoles createEntity(EntityManager em) {
        UserRoles userRoles = new UserRoles()
            .idUser(DEFAULT_ID_USER)
            .idRole(DEFAULT_ID_ROLE);
        return userRoles;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserRoles createUpdatedEntity(EntityManager em) {
        UserRoles userRoles = new UserRoles()
            .idUser(UPDATED_ID_USER)
            .idRole(UPDATED_ID_ROLE);
        return userRoles;
    }

    @BeforeEach
    public void initTest() {
        userRoles = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserRoles() throws Exception {
        int databaseSizeBeforeCreate = userRolesRepository.findAll().size();
        // Create the UserRoles
        restUserRolesMockMvc.perform(post("/api/user-roles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userRoles)))
            .andExpect(status().isCreated());

        // Validate the UserRoles in the database
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        assertThat(userRolesList).hasSize(databaseSizeBeforeCreate + 1);
        UserRoles testUserRoles = userRolesList.get(userRolesList.size() - 1);
        assertThat(testUserRoles.getIdUser()).isEqualTo(DEFAULT_ID_USER);
        assertThat(testUserRoles.getIdRole()).isEqualTo(DEFAULT_ID_ROLE);
    }

    @Test
    @Transactional
    public void createUserRolesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userRolesRepository.findAll().size();

        // Create the UserRoles with an existing ID
        userRoles.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserRolesMockMvc.perform(post("/api/user-roles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userRoles)))
            .andExpect(status().isBadRequest());

        // Validate the UserRoles in the database
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        assertThat(userRolesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserRoles() throws Exception {
        // Initialize the database
        userRolesRepository.saveAndFlush(userRoles);

        // Get all the userRolesList
        restUserRolesMockMvc.perform(get("/api/user-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userRoles.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUser").value(hasItem(DEFAULT_ID_USER.intValue())))
            .andExpect(jsonPath("$.[*].idRole").value(hasItem(DEFAULT_ID_ROLE.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserRoles() throws Exception {
        // Initialize the database
        userRolesRepository.saveAndFlush(userRoles);

        // Get the userRoles
        restUserRolesMockMvc.perform(get("/api/user-roles/{id}", userRoles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userRoles.getId().intValue()))
            .andExpect(jsonPath("$.idUser").value(DEFAULT_ID_USER.intValue()))
            .andExpect(jsonPath("$.idRole").value(DEFAULT_ID_ROLE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserRoles() throws Exception {
        // Get the userRoles
        restUserRolesMockMvc.perform(get("/api/user-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserRoles() throws Exception {
        // Initialize the database
        userRolesService.save(userRoles);

        int databaseSizeBeforeUpdate = userRolesRepository.findAll().size();

        // Update the userRoles
        UserRoles updatedUserRoles = userRolesRepository.findById(userRoles.getId()).get();
        // Disconnect from session so that the updates on updatedUserRoles are not directly saved in db
        em.detach(updatedUserRoles);
        updatedUserRoles
            .idUser(UPDATED_ID_USER)
            .idRole(UPDATED_ID_ROLE);

        restUserRolesMockMvc.perform(put("/api/user-roles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserRoles)))
            .andExpect(status().isOk());

        // Validate the UserRoles in the database
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        assertThat(userRolesList).hasSize(databaseSizeBeforeUpdate);
        UserRoles testUserRoles = userRolesList.get(userRolesList.size() - 1);
        assertThat(testUserRoles.getIdUser()).isEqualTo(UPDATED_ID_USER);
        assertThat(testUserRoles.getIdRole()).isEqualTo(UPDATED_ID_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserRoles() throws Exception {
        int databaseSizeBeforeUpdate = userRolesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserRolesMockMvc.perform(put("/api/user-roles").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userRoles)))
            .andExpect(status().isBadRequest());

        // Validate the UserRoles in the database
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        assertThat(userRolesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserRoles() throws Exception {
        // Initialize the database
        userRolesService.save(userRoles);

        int databaseSizeBeforeDelete = userRolesRepository.findAll().size();

        // Delete the userRoles
        restUserRolesMockMvc.perform(delete("/api/user-roles/{id}", userRoles.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserRoles> userRolesList = userRolesRepository.findAll();
        assertThat(userRolesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
