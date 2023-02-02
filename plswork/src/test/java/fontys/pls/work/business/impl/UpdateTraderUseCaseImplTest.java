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
public class UpdateTraderUseCaseImplTest {

    @Mock
    private TraderRepository traderRepository;

    @Mock
    private GetTraderUseCase getTraderUseCase;

    @InjectMocks
    private UpdateTraderUseCaseImpl updateTraderUseCase;

    @Test
    void shouldUpdateTraderPaymentInfo() {

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
                .nameOnCard("bandi dod")
                .cardNumber("1212-1212-1212-1212")
                .cardCVV("111")
                .cardValidThru("10/25")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        when(traderRepository.updateTrader(traderAfterChange)).thenReturn(traderAfterChange);
        when(getTraderUseCase.getTrader(traderBeforeChange.getId())).thenReturn(Optional.of(traderBeforeChange));

        Trader updated = updateTraderUseCase.updateTrader(1L, "bandi dod", "1212-1212-1212-1212", "111", "10/25");

        assertThat(updated).isEqualTo(traderAfterChange);
        verify(traderRepository).updateTrader(traderAfterChange);
    }
}
