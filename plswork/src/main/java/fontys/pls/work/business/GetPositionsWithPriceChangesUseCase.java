package fontys.pls.work.business;

import fontys.pls.work.domain.Position;

import java.util.List;

public interface GetPositionsWithPriceChangesUseCase {
    List<Position> getPriceChangesForPositions(List<Position> positions);
}
