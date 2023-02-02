package fontys.pls.work.business.impl;

import fontys.pls.work.business.CreateUserUseCase;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class    CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void shouldCreateUserWithAllFields() {
        UserD user = UserD.builder()
                .email("asd@mail.com")
                .password("123")
                .role("admin")
                .build();

        when(userRepository.create(Mockito.any(UserD.class))).thenReturn(user);

        UserD created = userRepository.create(user);

        assertThat(created).isEqualTo(user);
        verify(userRepository).create(user);
    }
}
