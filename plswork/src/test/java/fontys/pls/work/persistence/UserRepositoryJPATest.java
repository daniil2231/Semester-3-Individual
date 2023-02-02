package fontys.pls.work.persistence;

import fontys.pls.work.persistence.Entity.TraderEntity;
import fontys.pls.work.persistence.Entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryJPATest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepositoryJPA userRepositoryJPA;

    @Test
    void save_shouldSaveUserWithAllFields() {

        UserEntity user = UserEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .role("admin")
                .build();

        UserEntity savedUser = userRepositoryJPA.save(user);
        assertNotNull(savedUser.getId());

        savedUser = entityManager.find(UserEntity.class, savedUser.getId());
        UserEntity expectedUser = UserEntity.builder()
                .id(savedUser.getId())
                .email("test@mail.com")
                .password("1234")
                .role("admin")
                .build();

        assertEquals(expectedUser, savedUser);
    }

    @Test
    void findByEmail_shouldReturnUser_ifExists() {

        UserEntity user = UserEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .role("admin")
                .build();

        UserEntity savedUser = userRepositoryJPA.save(user);
        assertNotNull(savedUser.getId());

        UserEntity expected = userRepositoryJPA.findByEmail(savedUser.getEmail());

        assertEquals(expected, savedUser);
    }
}
