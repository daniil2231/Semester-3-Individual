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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class GetPositionUseCaseImplTest {

    @Mock
    private PositionRepository positionRepository;

    @InjectMocks
    private GetPositionUseCaseImpl getPositionUseCase;

    @Test
    void shouldReturnPositionWithProvidedId() {
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

        Optional<Position> position = Optional.ofNullable(Position.builder()
                .id(1L)
                .positionType("short")
                .val(1000.0)
                .liquidationPrice(30000.0)
                .entryPrice(15000.0)
                .changeInPrice(10.0)
                .trader(trader)
                .build());

        when(positionRepository.getPosition(1L)).thenReturn(position);

        Optional<Position> actual = positionRepository.getPosition(1L);

        assertEquals(actual, position);
        verify(positionRepository).getPosition(1L);
    }
}
