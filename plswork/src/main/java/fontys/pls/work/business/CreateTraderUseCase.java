package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

public interface CreateTraderUseCase {
    Trader createTrader(String email, String password, String nameOnCard, String cardNumber, String cardCVV, String cardValidThru);
}
