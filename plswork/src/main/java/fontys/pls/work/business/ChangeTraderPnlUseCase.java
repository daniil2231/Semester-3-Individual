package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

public interface ChangeTraderPnlUseCase {
    Trader changeTraderPnl(Double amount, Long id);
}
