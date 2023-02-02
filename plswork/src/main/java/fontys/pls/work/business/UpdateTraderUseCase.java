package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

public interface UpdateTraderUseCase {
    Trader updateTrader(Long id, String nameOnCard, String cardNumber, String cardCVV, String cardValidThru);
}
