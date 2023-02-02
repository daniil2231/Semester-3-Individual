package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

public interface ChangeTraderTradedVolumeUseCase {
    Trader changeTraderTradedVolume(Double amount, Long id);
}
