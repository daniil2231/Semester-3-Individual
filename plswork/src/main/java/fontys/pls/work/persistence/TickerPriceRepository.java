package fontys.pls.work.persistence;

import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.Entity.BinanceTickerPriceEntity;

import java.util.Optional;

public interface TickerPriceRepository {
    Optional<TickerPrice> getCurrentPrice();
}
