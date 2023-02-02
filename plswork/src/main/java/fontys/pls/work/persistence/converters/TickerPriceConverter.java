package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.Entity.BinanceTickerPriceEntity;

public class TickerPriceConverter {
    private TickerPriceConverter() { }

    public static TickerPrice convert(BinanceTickerPriceEntity tickerPrice) {
        return TickerPrice.builder()
                .price(tickerPrice.getPrice())
                .symbol(tickerPrice.getSymbol())
                .build();
    }
}
