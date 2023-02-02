package fontys.pls.work.business;

import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;

import java.util.List;

public interface GetPositionsByTraders_idUseCase {
    List<Position> getPositionsByTraders_id(Long id);
}
