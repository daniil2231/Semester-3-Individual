package fontys.pls.work.controller.converters;

import fontys.pls.work.controller.DTO.LoginResponseDTO;
import fontys.pls.work.controller.DTO.LoginResponseDTOConverter;
import fontys.pls.work.domain.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseDTOConverterTest {
    @Test
    void shouldConvertAllLoginResponseFieldsToDTO() {
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken("asd")
                .build();

        LoginResponseDTO actual = LoginResponseDTOConverter.convert(loginResponse);

        LoginResponseDTO expected = LoginResponseDTO.builder()
                .accessToken("asd")
                .build();

        assertEquals(actual, expected);
    }
}
