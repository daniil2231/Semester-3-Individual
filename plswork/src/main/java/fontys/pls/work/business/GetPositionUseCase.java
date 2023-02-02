package fontys.pls.work.business;

import fontys.pls.work.domain.Position;

import java.util.Optional;

public interface GetPositionUseCase {
    Optional<Position> getPosition(Long id);
}
