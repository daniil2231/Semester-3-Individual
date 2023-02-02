package fontys.pls.work.business.impl;

import fontys.pls.work.domain.TickerPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GetTickerPriceUseCaseImplTest {

    @Autowired
    GetTickerPriceUseCaseImpl getTickerPriceUseCase;

    @Test
    void shouldReturnTickerPrice() {
        Optional<TickerPrice> actual = getTickerPriceUseCase.getTickerPrice();

        Optional<TickerPrice> expected = Optional.ofNullable(TickerPrice.builder()
                .symbol("BTCUSDT")
                .price(actual.get().getPrice()) //The returned price cannot be predicted, so I think I have to do it like this
                .build());

        assertEquals(actual, expected);
    }
}
