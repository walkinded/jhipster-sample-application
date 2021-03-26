package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class UserRolesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRoles.class);
        UserRoles userRoles1 = new UserRoles();
        userRoles1.setId(1L);
        UserRoles userRoles2 = new UserRoles();
        userRoles2.setId(userRoles1.getId());
        assertThat(userRoles1).isEqualTo(userRoles2);
        userRoles2.setId(2L);
        assertThat(userRoles1).isNotEqualTo(userRoles2);
        userRoles1.setId(null);
        assertThat(userRoles1).isNotEqualTo(userRoles2);
    }
}
