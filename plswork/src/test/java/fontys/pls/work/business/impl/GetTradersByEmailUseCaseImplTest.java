package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTradersByEmailUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetTradersByEmailUseCaseImplTest {

    @Mock
    private TraderRepository traderRepository;

    @InjectMocks
    private GetTradersByEmailUseCaseImpl getTradersByEmailUseCase;

    @Test
    void shouldReturnTraders_FilteredByEmail() {

        Trader trader1 = Trader.builder()
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

        Trader trader2 = Trader.builder()
                .id(2L)
                .email("zzz@mail.com")
                .password("123")
                .nameOnCard("bandi dod")
                .cardNumber("1212-1212-1212-1212")
                .cardCVV("111")
                .cardValidThru("10/25")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        when(traderRepository.getTradersByEmail("asd")).thenReturn(List.of(trader1));
        when(getTradersByEmailUseCase.getTradersByEmail("asd")).thenReturn(List.of(trader1));

        List<Trader> actual = getTradersByEmailUseCase.getTradersByEmail("asd");

        List<Trader> expected = List.of(trader1);

        assertEquals(expected, actual);
        verify(traderRepository).getTradersByEmail("asd");
    }
}
