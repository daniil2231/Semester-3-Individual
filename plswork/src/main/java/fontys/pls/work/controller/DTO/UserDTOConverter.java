package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.UserD;

public class UserDTOConverter {

    private UserDTOConverter() {
    }

    public static UserDTO convert(UserD user) {

        if(user.getTrader().isPresent()) {
            return UserDTO.builder()
                    .id(user.getId())
                    .trader(TraderDTOConverter.convert(user.getTrader().get()))
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        }
        else {
            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        }
    }
}
