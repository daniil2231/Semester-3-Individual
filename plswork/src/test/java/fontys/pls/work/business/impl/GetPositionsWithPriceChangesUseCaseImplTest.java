package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.domain.Trader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetPositionsWithPriceChangesUseCaseImplTest {

    @Mock
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @InjectMocks
    private GetPositionsWithPriceChangesUseCaseImpl getPositionsWithPriceChangesUseCase;

    @Test
    void shouldReturnAllPositionsWithPriceChangesSinceTheirOpening() {
        TickerPrice tp = TickerPrice.builder()
                .symbol("BTCUSDT")
                .price(15000.0)
                .build();

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

        Position position1 = Position.builder()
                .id(1L)
                .positionType("short")
                .val(1000.0)
                .entryPrice(30000.0)
                .liquidationPrice(29925.0)
                .trader(trader)
                .changeInPrice(100.0)
                .build();

        Position position2 = Position.builder()
                .id(2L)
                .positionType("short")
                .val(1000.0)
                .entryPrice(15000.0)
                .liquidationPrice(29925.0)
                .trader(trader)
                .changeInPrice(0.0)
                .build();


        when(getTickerPriceUseCase.getTickerPrice()).thenReturn(Optional.ofNullable(tp));

        List<Position> actual = getPositionsWithPriceChangesUseCase.getPriceChangesForPositions(List.of(position1, position2));

        assertEquals(actual, List.of(position1, position2));
    }
}
