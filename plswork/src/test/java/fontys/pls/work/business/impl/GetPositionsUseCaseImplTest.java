package fontys.pls.work.business.impl;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetPositionsUseCaseImplTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private GetPositionsUseCaseImpl getPositionsUseCase;

    @Test
    void shouldReturnAllPositions() {

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

        when(positionRepository.getPositions()).thenReturn(List.of(position1, position2));
        when(getPositionsUseCase.getPositions()).thenReturn(List.of(position1, position2));

        List<Position> actual = getPositionsUseCase.getPositions();

        List<Position> expected = List.of(position1, position2);

        assertEquals(expected, actual);
        verify(positionRepository).getPositions();
    }
}
