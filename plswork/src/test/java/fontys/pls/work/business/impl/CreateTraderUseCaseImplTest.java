package fontys.pls.work.business.impl;

import fontys.pls.work.domain.Trader;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.TraderRepository;
import fontys.pls.work.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreateTraderUseCaseImplTest {

    @Mock
    private TraderRepository traderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateTraderUseCaseImpl createTraderUseCase;

    @Test
    void shouldCreateTraderWithAllFields() {
        Trader trader = Trader.builder()
                .email("asd@mail.com")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        UserD newUser = UserD.builder()
                .email(trader.getEmail())
                .password(trader.getPassword())
                .trader(Optional.of(trader))
                .role("trader")
                .build();

        when(traderRepository.createTrader(Mockito.any(Trader.class))).thenReturn(trader);

        Trader created = createTraderUseCase.createTrader("asd@mail.com", "123", "dandi bob", "3434-3434-3434-3434", "999", "09/24");

        assertThat(created).isEqualTo(trader);
        verify(userRepository).create(newUser);
    }
}
