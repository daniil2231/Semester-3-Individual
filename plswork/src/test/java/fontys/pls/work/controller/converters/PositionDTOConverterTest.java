package fontys.pls.work.controller.converters;

import fontys.pls.work.controller.DTO.PositionDTO;
import fontys.pls.work.controller.DTO.PositionDTOConverter;
import fontys.pls.work.controller.DTO.TraderDTO;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.PositionEntity;
import fontys.pls.work.persistence.Entity.TraderEntity;
import fontys.pls.work.persistence.converters.PositionConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionDTOConverterTest {

    @Test
    void shouldConvertAllPositionFieldsToDTO() {
        Trader traderD = createTraderDomain();

        Position positionToBeConverted = Position.builder()
                .id(1L)
                .trader(traderD)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();

        PositionDTO actual = PositionDTOConverter.convert(positionToBeConverted);

        TraderDTO traderDTO = createTraderDTO();

        PositionDTO expected = PositionDTO.builder()
                .id(1L)
                .trader(traderDTO)
                .positionType("short")
                .val(100.0)
                .entryPrice(20000.0)
                .liquidationPrice(30000.0)
                .build();
        assertEquals(expected, actual);
    }

    TraderDTO createTraderDTO() {
        TraderDTO traderDTO = TraderDTO.builder()
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

        return traderDTO;
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
