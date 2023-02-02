package fontys.pls.work.business.impl;

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
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetTraderUseCaseImplTest {

    @Mock
    private TraderRepository traderRepository;

    @InjectMocks
    private GetTraderUseCaseImpl getTraderUseCase;

    @Test
    void shouldReturnTraderWithProvidedId() {
        Optional<Trader> trader = Optional.ofNullable(Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build());

        when(traderRepository.getTrader(1L)).thenReturn(trader);

        Optional<Trader> actual = getTraderUseCase.getTrader(1L);

        assertEquals(actual, trader);
        verify(traderRepository).getTrader(1L);
    }
}
