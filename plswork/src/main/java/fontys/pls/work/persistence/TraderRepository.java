package fontys.pls.work.persistence;

import fontys.pls.work.domain.TotalTradedVolumeResponse;
import fontys.pls.work.domain.Trader;

import java.util.List;
import java.util.Optional;

public interface TraderRepository {
    Trader createTrader(Trader trader);

    Trader updateTrader(Trader trader);

    Optional<Trader> getTrader(Long id);

    List<Trader> getTraders();

    List<Trader> getTradersByEmail(String email);

    //Boolean deleteTrader(Long id);

    Trader changeTraderFundsPnlVolume(Trader trader);

    TotalTradedVolumeResponse sumTotalTradedVolume();
}
