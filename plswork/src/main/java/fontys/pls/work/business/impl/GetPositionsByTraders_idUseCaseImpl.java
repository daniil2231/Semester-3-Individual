package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetPositionsByTraders_idUseCase;
import fontys.pls.work.business.GetPositionsWithPriceChangesUseCase;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.persistence.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GetPositionsByTraders_idUseCaseImpl implements GetPositionsByTraders_idUseCase {

    @Autowired
    private GetPositionsWithPriceChangesUseCase getPositionsWithPriceChangesUseCase;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    @Autowired
    private PositionRepository repo;

    public List<Position> getPositionsByTraders_id(Long id) {
        return getPositionsWithPriceChangesUseCase.getPriceChangesForPositions(repo.getPositionsByTraders_id(getTraderUseCase.getTrader(id).get()));
    }
}
