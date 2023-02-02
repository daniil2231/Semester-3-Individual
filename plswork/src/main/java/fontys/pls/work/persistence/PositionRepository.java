package fontys.pls.work.persistence;

import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;

import java.util.List;
import java.util.Optional;

public interface PositionRepository {

    List<Position> getPositions();

    List<Position> getPositionsByTraders_id(Trader trader);

    Position createPosition(Position position);

    Boolean closePosition(Long id);

    Optional<Position> getPosition(Long id);
}
