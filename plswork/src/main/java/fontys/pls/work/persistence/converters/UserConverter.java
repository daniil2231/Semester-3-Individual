package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.UserEntity;

import java.util.Optional;

public class UserConverter {
    private UserConverter() {
    }

    public static UserD convert(UserEntity user) {
        if(user.getTrader() != null) {
            return UserD.builder()
                    .id(user.getId())
                    .trader(Optional.ofNullable(TraderConverter.convert(user.getTrader())))
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        }
        else {
            return UserD.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        }
    }
}
