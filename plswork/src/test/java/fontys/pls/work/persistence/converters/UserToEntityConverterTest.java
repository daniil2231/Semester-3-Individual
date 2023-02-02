package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Trader;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.TraderEntity;
import fontys.pls.work.persistence.Entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserToEntityConverterTest {

    @Test
    void shouldConvertAllUserFieldsToEntity() {
        UserD userToBeConverted = UserD.builder()
                .id(1L)
                .email("test@mail.com")
                .password("123")
                .trader(Optional.ofNullable(createTraderDomain()))
                .role("trader")
                .build();

        UserEntity actual = UserToEntityConverter.convert(userToBeConverted);

        UserEntity expected = UserEntity.builder()
                .id(1L)
                .email("test@mail.com")
                .password("123")
                .trader(createTraderEntity())
                .role("trader")
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
