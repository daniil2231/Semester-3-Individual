package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class ChangeTraderPnlUseCaseImplTest {

    @Mock
    private GetTraderUseCase getTraderUseCase;

    @Mock
    private TraderRepository traderRepository;

    @InjectMocks
    private ChangeTraderPnlUseCaseImpl changeTraderPnlUseCase;

    @Test
    void shouldChangeTraderPnl() {

        Trader traderBeforeChange = Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("123")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        Trader traderAfterChange = Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("123")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(1000.0)
                .tradedVolume(0.0)
                .build();

        when(traderRepository.changeTraderFundsPnlVolume(traderAfterChange)).thenReturn(traderAfterChange);
        when(getTraderUseCase.getTrader(traderBeforeChange.getId())).thenReturn(Optional.of(traderBeforeChange));

        Trader updated = changeTraderPnlUseCase.changeTraderPnl(1000.0, 1L);

        assertThat(updated).isEqualTo(traderAfterChange);
        verify(traderRepository).changeTraderFundsPnlVolume(traderAfterChange);
    }
}
