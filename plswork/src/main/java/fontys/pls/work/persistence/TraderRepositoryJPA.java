package fontys.pls.work.persistence;

import fontys.pls.work.persistence.Entity.TraderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TraderRepositoryJPA extends JpaRepository<TraderEntity, Long> {

    @Query(value = "SELECT SUM(t.tradedVolume) FROM TraderEntity t")
    Double sumTotalVolume();
}
