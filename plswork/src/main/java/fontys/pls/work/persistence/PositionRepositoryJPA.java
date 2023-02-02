package fontys.pls.work.persistence;

import fontys.pls.work.persistence.Entity.PositionEntity;
import fontys.pls.work.persistence.Entity.TraderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepositoryJPA extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findAllByTrader(TraderEntity trader);
}
