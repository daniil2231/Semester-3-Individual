package fontys.pls.work.business.impl;

import fontys.pls.work.business.ChangeTraderFundsUseCase;
import fontys.pls.work.business.GetPositionUseCase;
import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.PositionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class ClosePositionUseCaseImplTest {

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private GetPositionUseCase getPositionUseCase;

    @Mock
    private GetTraderUseCase getTraderUseCase;

    @Mock
    private ChangeTraderFundsUseCase changeTraderFundsUseCase;

    @Mock
    private ChangeTraderPnlUseCaseImpl changeTraderPnlUseCase;

    @Mock
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @InjectMocks
    private ClosePositionUseCaseImpl closePositionUseCase;

    @Test
    void shouldClosePosition() {

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

        Position position = Position.builder()
                .id(1L)
                .positionType("short")
                .val(1000.0)
                .entryPrice(14000.0)
                .liquidationPrice(29925.0)
                .changeInPrice(0.0)
                .trader(trader)
                .changeInPrice(null)
                .build();

        TickerPrice tp = TickerPrice.builder()
                .symbol("BTCUSDT")
                .price(15000.0)
                .build();

        when(getPositionUseCase.getPosition(1L)).thenReturn(Optional.ofNullable(position));
        when(getTraderUseCase.getTrader(1L)).thenReturn(Optional.ofNullable(trader));
        when(getTickerPriceUseCase.getTickerPrice()).thenReturn(Optional.ofNullable(tp));
        when(changeTraderFundsUseCase.changeTraderFunds(1000.0, 1L)).thenReturn(trader);
        when(changeTraderPnlUseCase.changeTraderPnl(1000.0, 1L)).thenReturn(trader);
        when(closePositionUseCase.closePosition(position.getId())).thenReturn(true);

        Boolean deletion = closePositionUseCase.closePosition(position.getId());

        assertThat(deletion.equals(true));
        verify(positionRepository).closePosition(position.getId());
    }
}
