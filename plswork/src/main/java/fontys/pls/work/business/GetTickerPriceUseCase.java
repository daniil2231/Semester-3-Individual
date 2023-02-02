package fontys.pls.work.business;

import fontys.pls.work.domain.TickerPrice;

import java.util.Optional;

public interface GetTickerPriceUseCase {
    Optional<TickerPrice> getTickerPrice();
}
