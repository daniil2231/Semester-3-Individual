package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.Entity.BinanceTickerPriceEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TickerPriceConverterTest {
    @Test
    void shouldConvertAllTickerPriceFieldsToDomain() {
        BinanceTickerPriceEntity priceToBeConverted = BinanceTickerPriceEntity.builder()
                .price(20000.0)
                .symbol("BTCUSD")
                .build();

        TickerPrice actual = TickerPriceConverter.convert(priceToBeConverted);

        TickerPrice expected = TickerPrice.builder()
                .price(20000.0)
                .symbol("BTCUSD")
                .build();
        assertEquals(actual, expected);
    }
}
