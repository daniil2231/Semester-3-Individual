package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.UserEntity;

public class UserToEntityConverter {
    private UserToEntityConverter() {
    }

    public static UserEntity convert(UserD user) {
        return UserEntity.builder()
                .id(user.getId())
                .trader(TraderToEntityConverter.convert(user.getTrader().get()))
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
