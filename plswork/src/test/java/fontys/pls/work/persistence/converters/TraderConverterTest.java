package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TraderConverterTest {

    @Test
    void shouldConvertAllTraderFieldsToDomain() {
        TraderEntity traderToBeConverted = TraderEntity.builder()
                .id(1L)
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("333")
                .cardValidThru("09/23")
                .tradedVolume(50000.5)
                .realizedPnl(-10000.5)
                .funds(5000.5)
                .build();

        Trader actual = TraderConverter.convert(traderToBeConverted);

        Trader expected = Trader.builder()
                .id(1L)
                .email("test@mail.com")
                .password("1234")
                .nameOnCard("dandi bobandi")
                .cardNumber("1234-1234-1234-1234")
                .cardCVV("333")
                .cardValidThru("09/23")
                .tradedVolume(50000.5)
                .realizedPnl(-10000.5)
                .funds(5000.5)
                .build();
        assertEquals(expected, actual);
    }
}
