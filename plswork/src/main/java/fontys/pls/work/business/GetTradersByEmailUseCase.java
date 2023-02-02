package fontys.pls.work.business;

import fontys.pls.work.domain.Trader;

import java.util.List;

public interface GetTradersByEmailUseCase {

    List<Trader> getTradersByEmail(String email);
}
