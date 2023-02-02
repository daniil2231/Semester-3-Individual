package fontys.pls.work.persistence;

import fontys.pls.work.domain.TotalTradedVolumeResponse;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TraderRepositoryJPATest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TraderRepositoryJPA traderRepositoryJPA;

    @Test
    void save_shouldSaveTraderWithAllFields() {
        TraderEntity trader = TraderEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("555")
                .cardValidThru("10/25")
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        TraderEntity savedTrader = traderRepositoryJPA.save(trader);
        assertNotNull(savedTrader.getId());

        savedTrader = entityManager.find(TraderEntity.class, savedTrader.getId());
        TraderEntity expectedTrader = TraderEntity.builder()
                .id(savedTrader.getId())
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("555")
                .cardValidThru("10/25")
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        assertEquals(expectedTrader, savedTrader);
    }

    @Test
    void findById_shouldReturnTrader_ifExists() {

        TraderEntity trader = TraderEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("555")
                .cardValidThru("10/25")
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        TraderEntity savedTrader = traderRepositoryJPA.save(trader);
        assertNotNull(savedTrader.getId());

        Optional<TraderEntity> expectedTrader = traderRepositoryJPA.findById(savedTrader.getId());

        assertEquals(expectedTrader.get(), savedTrader);
    }

    @Test
    void findAll_shouldFindAllSavedTraders() {

        TraderEntity trader1 = TraderEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("555")
                .cardValidThru("10/25")
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        TraderEntity trader2 = TraderEntity.builder()
                .email("test1@mail.com")
                .password("12345")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("777")
                .cardValidThru("10/26")
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(1000.0)
                .build();

        traderRepositoryJPA.save(trader1);
        traderRepositoryJPA.save(trader2);

        assertEquals(List.of(trader1, trader2), traderRepositoryJPA.findAll());
    }

    // not implemented
//    @Test
//    void deleteById_shouldDeleteTrader() {
//        TraderEntity trader = TraderEntity.builder()
//                .email("test@mail.com")
//                .password("1234")
//                .nameOnCard("dandi bobandi")
//                .cardNumber("1234-1234-1234-1234")
//                .cardCVV("555")
//                .cardValidThru("10/25")
//                .tradedVolume(0.0)
//                .realizedPnl(0.0)
//                .funds(0.0)
//                .build();
//
//        TraderEntity savedTrader = traderRepositoryJPA.save(trader);
//        assertNotNull(savedTrader.getId());
//        traderRepositoryJPA.deleteById(savedTrader.getId());
//
//        savedTrader = entityManager.find(TraderEntity.class, savedTrader.getId());
//
//        assertNull(savedTrader);
//    }

    @Test
    void sumTotalVolume_shouldReturnTotalTradedVolume() {
        TraderEntity trader1 = TraderEntity.builder()
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("555")
                .cardValidThru("10/25")
                .tradedVolume(1000.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        TraderEntity trader2 = TraderEntity.builder()
                .email("test1@mail.com")
                .password("12345")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("777")
                .cardValidThru("10/26")
                .tradedVolume(500.0)
                .realizedPnl(0.0)
                .funds(1000.0)
                .build();

        traderRepositoryJPA.save(trader1);
        traderRepositoryJPA.save(trader2);

        TotalTradedVolumeResponse expected = TotalTradedVolumeResponse.builder()
                .totalTradedVolume(trader1.getTradedVolume() + trader2.getTradedVolume())
                .build();

        Double actual = traderRepositoryJPA.sumTotalVolume();

        assertEquals(expected.getTotalTradedVolume(), actual);
    }
}
