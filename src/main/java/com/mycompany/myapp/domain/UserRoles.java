package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A UserRoles.
 */
@Entity
@Table(name = "user_roles")
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_role")
    private Long idRole;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public UserRoles idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRole() {
        return idRole;
    }

    public UserRoles idRole(Long idRole) {
        this.idRole = idRole;
        return this;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRoles)) {
            return false;
        }
        return id != null && id.equals(((UserRoles) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserRoles{" +
            "id=" + getId() +
            ", idUser=" + getIdUser() +
            ", idRole=" + getIdRole() +
            "}";
    }
}
