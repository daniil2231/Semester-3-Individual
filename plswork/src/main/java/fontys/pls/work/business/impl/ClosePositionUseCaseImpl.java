package fontys.pls.work.business.impl;

import fontys.pls.work.business.*;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClosePositionUseCaseImpl implements ClosePositionUseCase {
    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private GetPositionUseCase getPositionUseCase;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    @Autowired
    private ChangeTraderFundsUseCase changeTraderFundsUseCase;

    @Autowired
    private ChangeTraderPnlUseCase changeTraderPnlUseCase;

    @Autowired
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @Override
    @Transactional
    public Boolean closePosition(Long id) {
        Optional<Position> position = getPositionUseCase.getPosition(id);

        Optional<Trader> trader = getTraderUseCase.getTrader(position.get().getTrader().getId());

        Double pnl = getTickerPriceUseCase.getTickerPrice().get().getPrice() - position.get().getEntryPrice();

        changeTraderFundsUseCase.changeTraderFunds(pnl, trader.get().getId());

        changeTraderPnlUseCase.changeTraderPnl(pnl, trader.get().getId());

        return positionRepository.closePosition(id);
    }
}
