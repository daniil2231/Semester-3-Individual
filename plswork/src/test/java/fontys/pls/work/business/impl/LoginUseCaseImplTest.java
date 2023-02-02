package fontys.pls.work.business.impl;

import fontys.pls.work.business.AccessTokenEncoder;
import fontys.pls.work.domain.LoginResponse;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class LoginUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void shouldReturnLoginResponseSuccessfully() {

        Trader trader = Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("123")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(1000.0)
                .realizedPnl(1000.0)
                .tradedVolume(0.0)
                .build();

        UserD userD = UserD.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("encoded123password")
                .role("trader")
                .trader(Optional.ofNullable(trader))
                .build();

        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(null)
                .role("trader")
                .build();

        when(userRepository.login("asd@mail.com", "123")).thenReturn(userD);
        when(passwordEncoder.matches("123", "encoded123password")).thenReturn(true);

        LoginResponse actual = loginUseCase.login("asd@mail.com", "123");

        assertEquals(actual, loginResponse);
        verify(userRepository).login("asd@mail.com", "123");
    }
}
