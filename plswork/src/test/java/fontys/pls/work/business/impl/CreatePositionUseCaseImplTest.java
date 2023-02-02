package fontys.pls.work.business.impl;

import fontys.pls.work.business.ChangeTraderFundsUseCase;
import fontys.pls.work.business.ChangeTraderTradedVolumeUseCase;
import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.PositionRepository;
import fontys.pls.work.persistence.TickerPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class CreatePositionUseCaseImplTest {

    @Mock
    private TickerPriceRepository tickerPriceRepository;

    @Mock
    private ChangeTraderTradedVolumeUseCase changeTraderTradedVolumeUseCase;

    @Mock
    private ChangeTraderFundsUseCase changeTraderFundsUseCase;

    @Mock
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @Mock
    private PositionRepository positionRepo;

    @Mock
    private GetTraderUseCase getTraderUseCase;

    @InjectMocks
    private CreatePositionUseCaseImpl createPositionUseCase;

    @Test
    void shouldCreateShortPositionWithAllFields() {

        Trader trader = Trader.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("123")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(1000.0)
                .realizedPnl(0.0)
                .tradedVolume(0.0)
                .build();

        Position position = Position.builder()
                .positionType("short")
                .val(1000.0)
                .entryPrice(15000.0)
                .liquidationPrice(29925.0)
                .changeInPrice(0.0)
                .trader(trader)
                .changeInPrice(null)
                .build();

        TickerPrice tp = TickerPrice.builder()
                .symbol("BTCUSDT")
                .price(15000.0)
                .build();

        when(getTickerPriceUseCase.getTickerPrice()).thenReturn(Optional.ofNullable(tp));
        when(getTraderUseCase.getTrader(trader.getId())).thenReturn(Optional.of(trader));
        when(changeTraderFundsUseCase.changeTraderFunds(-1000.0, 1L)).thenReturn(trader);
        when(positionRepo.createPosition(position)).thenReturn(position);

        Position created = createPositionUseCase.createPosition(1L, 1000.0, "short");

        assertThat(created).isEqualTo(position);
        verify(positionRepo).createPosition(position);
    }
}
