package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.UserRolesService;
import com.mycompany.myapp.domain.UserRoles;
import com.mycompany.myapp.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserRoles}.
 */
@Service
@Transactional
public class UserRolesServiceImpl implements UserRolesService {

    private final Logger log = LoggerFactory.getLogger(UserRolesServiceImpl.class);

    private final UserRolesRepository userRolesRepository;

    public UserRolesServiceImpl(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }

    @Override
    public UserRoles save(UserRoles userRoles) {
        log.debug("Request to save UserRoles : {}", userRoles);
        return userRolesRepository.save(userRoles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRoles> findAll() {
        log.debug("Request to get all UserRoles");
        return userRolesRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserRoles> findOne(Long id) {
        log.debug("Request to get UserRoles : {}", id);
        return userRolesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserRoles : {}", id);
        userRolesRepository.deleteById(id);
    }
}
