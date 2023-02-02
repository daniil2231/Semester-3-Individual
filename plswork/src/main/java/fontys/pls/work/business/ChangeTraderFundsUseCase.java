package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

public interface ChangeTraderFundsUseCase {
    Trader changeTraderFunds(Double amountToAdd, Long id);
}
