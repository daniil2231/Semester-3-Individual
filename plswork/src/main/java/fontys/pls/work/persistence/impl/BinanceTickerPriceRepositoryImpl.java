package fontys.pls.work.persistence.impl;

import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.Entity.BinanceTickerPriceEntity;
import fontys.pls.work.persistence.TickerPriceRepository;
import fontys.pls.work.persistence.converters.TickerPriceConverter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
@AllArgsConstructor
@Qualifier("binanceRepo")
public class BinanceTickerPriceRepositoryImpl implements TickerPriceRepository {

    private static final String BINANCE_TICKER_PRICE_URL = "https://api2.binance.com/api/v3/ticker/price?symbol={symbol}";
    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceTickerPriceRepositoryImpl.class);
    private RestTemplate restTemplate;


    @Override
    public Optional<TickerPrice> getCurrentPrice() {
        //try {
            //Map<String, String> params = Map.of("symbol", fromCurrency + toCurrency);
            String params = "BTCUSDT";

            LOGGER.info("Calling Binance API for ticker {} price.", params);
            BinanceTickerPriceEntity tickerPrice = restTemplate.getForObject(BINANCE_TICKER_PRICE_URL, BinanceTickerPriceEntity.class, params);
            LOGGER.info("Called Binance API for ticker {} price with success.", params);

            if (tickerPrice == null || tickerPrice.getPrice() == null) {
                LOGGER.warn("Empty response {} for {}", tickerPrice, params);
                throw new NullPointerException();
            }

            BinanceTickerPriceEntity priceEntity = BinanceTickerPriceEntity.builder()
                    .symbol("BTCUSDT")
                    .price(tickerPrice.getPrice())
                    .build();

            return Optional.ofNullable(TickerPriceConverter.convert(priceEntity));

        /*} catch (HttpClientErrorException e) {
            LOGGER.error("Error calling Binance API: " + e.getResponseBodyAsString(), e);
            return null;
        }*/
    }
}
