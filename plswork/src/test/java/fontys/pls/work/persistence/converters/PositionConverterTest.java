package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.PositionEntity;
import fontys.pls.work.persistence.Entity.TraderEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionConverterTest {
    @Test
    void shouldConvertAllPositionFieldsToDomain() {
        TraderEntity traderEntity = createTraderEntity();

        PositionEntity positionToBeConverted = PositionEntity.builder()
                .id(1L)
                .trader(traderEntity)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        Position actual = PositionConverter.convert(positionToBeConverted);

        Trader traderD = createTraderDomain();

        Position expected = Position.builder()
                .id(1L)
                .trader(traderD)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();
        assertEquals(expected, actual);
    }

    TraderEntity createTraderEntity() {
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

        return traderEntity;
    }

    Trader createTraderDomain() {
        Trader traderD = Trader.builder()
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

        return traderD;
    }
}
