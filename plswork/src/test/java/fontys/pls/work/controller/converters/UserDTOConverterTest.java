package fontys.pls.work.controller.converters;

import fontys.pls.work.controller.DTO.TraderDTO;
import fontys.pls.work.controller.DTO.UserDTO;
import fontys.pls.work.controller.DTO.UserDTOConverter;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.UserEntity;
import fontys.pls.work.persistence.converters.UserConverter;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOConverterTest {
    @Test
    void shouldConvertAllUserFieldsToDTO() {
        UserD userToBeConverted = UserD.builder()
                .id(1L)
                .email("test@mail.com")
                .password("123")
                .trader(Optional.ofNullable(createTraderDomain()))
                .role("trader")
                .build();

        UserDTO actual = UserDTOConverter.convert(userToBeConverted);

        UserDTO expected = UserDTO.builder()
                .id(1L)
                .email("test@mail.com")
                .password("123")
                .trader(createTraderDTO())
                .role("trader")
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
