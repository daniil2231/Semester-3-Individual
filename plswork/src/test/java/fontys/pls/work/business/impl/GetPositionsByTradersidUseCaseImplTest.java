package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetPositionsWithPriceChangesUseCase;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.PositionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetPositionsByTradersidUseCaseImplTest {

    @Mock
    private GetPositionsWithPriceChangesUseCase getPositionsWithPriceChangesUseCase;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private GetTraderUseCase getTraderUseCase;

    @InjectMocks
    private GetPositionsByTraders_idUseCaseImpl getPositionsByTraderUseCase;

    @Test
    void shouldReturnAllOfATradersPositions() {
        Trader trader = Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(0.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        Position position1 = Position.builder()
                .id(1L)
                .positionType("short")
                .val(1000.0)
                .liquidationPrice(30000.0)
                .entryPrice(15000.0)
                .changeInPrice(10.0)
                .trader(trader)
                .build();

        Position position2 = Position.builder()
                .id(2L)
                .positionType("long")
                .val(1000.0)
                .liquidationPrice(50.0)
                .entryPrice(15000.0)
                .changeInPrice(10.0)
                .trader(trader)
                .build();

        when(getTraderUseCase.getTrader(1L)).thenReturn(Optional.ofNullable(trader));
        when(positionRepository.getPositionsByTraders_id(trader)).thenReturn(List.of(position1, position2));
        when(getPositionsWithPriceChangesUseCase.getPriceChangesForPositions(List.of(position1, position2))).thenReturn(List.of(position1, position2));

        List<Position> actual = getPositionsByTraderUseCase.getPositionsByTraders_id(1L);

        List<Position> expected = List.of(position1, position2);

        assertEquals(expected, actual);
        verify(positionRepository).getPositionsByTraders_id(trader);
    }
}
