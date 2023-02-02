package fontys.pls.work.persistence;

import fontys.pls.work.persistence.Entity.PositionEntity;
import fontys.pls.work.persistence.Entity.TraderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PositionRepositoryJPATest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PositionRepositoryJPA positionRepositoryJPA;

    @Autowired
    private TraderRepositoryJPA traderRepositoryJPA;

    @Test
    void save_shouldSavePositionWithAllFields() {
        TraderEntity trader = createTrader();
        PositionEntity position = PositionEntity.builder()
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        PositionEntity savedPosition = positionRepositoryJPA.save(position);
        assertNotNull(savedPosition.getId());

        savedPosition = entityManager.find(PositionEntity.class, savedPosition.getId());
        PositionEntity expectedPosition = PositionEntity.builder()
                .id(savedPosition.getId())
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        assertEquals(expectedPosition, savedPosition);
    }

    @Test
    void find_shouldFindPositionById_WhenItExists() {
        TraderEntity trader = createTrader();
        PositionEntity position = PositionEntity.builder()
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        PositionEntity savedPosition = positionRepositoryJPA.save(position);
        assertNotNull(savedPosition.getId());

        Optional<PositionEntity> expectedPosition = positionRepositoryJPA.findById(savedPosition.getId());

        assertEquals(savedPosition.getId(), expectedPosition.get().getId());
        assertEquals(savedPosition.getPositionType(), expectedPosition.get().getPositionType());
        assertEquals(savedPosition.getVal(), expectedPosition.get().getVal());
        assertEquals(savedPosition.getEntryPrice(), expectedPosition.get().getEntryPrice());
        assertEquals(savedPosition.getLiquidationPrice(), expectedPosition.get().getLiquidationPrice());
    }

    @Test
    void findAll_ShouldFindAllSavedPositions() {
        TraderEntity trader = createTrader();
        List<PositionEntity> positions = new ArrayList<>();
        PositionEntity position1 = PositionEntity.builder()
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();
        positions.add(position1);
        PositionEntity position2 = PositionEntity.builder()
                .trader(trader)
                .positionType("long")
                .val(200.0)
                .entryPrice(10000.0)
                .liquidationPrice(20000.0)
                .build();
        positions.add(position2);

        positionRepositoryJPA.save(position1);
        positionRepositoryJPA.save(position2);

        assertEquals(positions, positionRepositoryJPA.findAll());
    }


    @Test
    void find_shouldReturnOptionalEmpty_WhenPositionIsNotSaved() {
        TraderEntity trader = createTrader();
        PositionEntity position = PositionEntity.builder()
                .id(1L)
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        Optional<PositionEntity> expectedPosition = positionRepositoryJPA.findById(position.getId());

        assertTrue(expectedPosition.isEmpty());
    }

    @Test
    void deleteById_shouldDeletePosition() {
        TraderEntity trader = createTrader();
        PositionEntity position = PositionEntity.builder()
                .id(1L)
                .trader(trader)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        PositionEntity savedPosition = positionRepositoryJPA.save(position);
        assertNotNull(savedPosition.getId());
        positionRepositoryJPA.deleteById(savedPosition.getId());

        savedPosition = entityManager.find(PositionEntity.class, savedPosition.getId());

        assertNull(savedPosition);
    }

    TraderEntity createTrader() {
        TraderEntity traderEntity = TraderEntity.builder()
                .id(1L)
                .email("asd@mail.com")
                .password("123")
                .nameOnCard("dandi bob")
                .cardNumber("3434-3434-3434-3434")
                .cardCVV("999")
                .cardValidThru("09/24")
                .funds(1000.0)
                .realizedPnl(500.0)
                .tradedVolume(2000.0)
                .build();

        traderRepositoryJPA.save(traderEntity);

        return traderEntity;
    }
}
