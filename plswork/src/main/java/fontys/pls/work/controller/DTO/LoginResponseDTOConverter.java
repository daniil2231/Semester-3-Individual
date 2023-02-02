package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.LoginResponse;
import fontys.pls.work.domain.Position;

public class LoginResponseDTOConverter {
    private LoginResponseDTOConverter() {
    }

    public static LoginResponseDTO convert(LoginResponse loginResponse) {
        return LoginResponseDTO.builder()
                .accessToken(loginResponse.getAccessToken())
                .role(loginResponse.getRole())
                .build();
    }
}
