package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

import java.util.Optional;

public interface GetTraderUseCase {
    Optional<Trader> getTrader(Long id);
}
