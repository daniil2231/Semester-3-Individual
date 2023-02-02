package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetPositionsWithPriceChangesUseCase;
import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.domain.Position;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPositionsWithPriceChangesUseCaseImpl implements GetPositionsWithPriceChangesUseCase {

    @Autowired
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @Override
    public List<Position> getPriceChangesForPositions(List<Position> positions) {
        Double tickerPrice = getTickerPriceUseCase.getTickerPrice().get().getPrice();

        for (Position position:
             positions) {
            Double pnlPercent = ((getTickerPriceUseCase.getTickerPrice().get().getPrice() - position.getEntryPrice()) / position.getEntryPrice()) * 100;

            position.setChangeInPrice(pnlPercent);
        }

        return positions;
    }
}
