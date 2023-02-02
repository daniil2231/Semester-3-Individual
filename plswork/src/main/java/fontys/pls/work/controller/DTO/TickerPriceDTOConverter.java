package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.TickerPrice;

public class TickerPriceDTOConverter {
    private TickerPriceDTOConverter() { }

    public static TickerPriceDTO convert(TickerPrice tickerPrice) {
        return TickerPriceDTO.builder()
                .price(tickerPrice.getPrice())
                .symbol(tickerPrice.getSymbol())
                .build();
    }
}
