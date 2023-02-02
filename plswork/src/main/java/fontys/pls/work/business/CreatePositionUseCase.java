package fontys.pls.work.business;

import fontys.pls.work.domain.Position;

public interface CreatePositionUseCase {
    Position createPosition(Long traderId, Double positionVal, String positionType);
}
