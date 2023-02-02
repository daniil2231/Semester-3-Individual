package fontys.pls.work.controller.converters;

import fontys.pls.work.controller.DTO.TickerPriceDTO;
import fontys.pls.work.controller.DTO.TickerPriceDTOConverter;
import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.Entity.BinanceTickerPriceEntity;
import fontys.pls.work.persistence.converters.TickerPriceConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TickerPriceDTOConverterTest {
    @Test
    void shouldConvertAllTickerPriceFieldsToDTO() {
        TickerPrice priceToBeConverted = TickerPrice.builder()
                .price(20000.0)
                .symbol("BTCUSD")
                .build();

        TickerPriceDTO actual = TickerPriceDTOConverter.convert(priceToBeConverted);

        TickerPriceDTO expected = TickerPriceDTO.builder()
                .price(20000.0)
                .symbol("BTCUSD")
                .build();
        assertEquals(actual, expected);
    }
}
