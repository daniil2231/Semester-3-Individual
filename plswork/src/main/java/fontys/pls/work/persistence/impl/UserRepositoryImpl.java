package fontys.pls.work.persistence.impl;

import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.UserEntity;
import fontys.pls.work.persistence.UserRepository;
import fontys.pls.work.persistence.UserRepositoryJPA;
import fontys.pls.work.persistence.converters.TraderToEntityConverter;
import fontys.pls.work.persistence.converters.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private UserRepositoryJPA userRepositoryJPA;

    @Override
    public UserD login(String email, String password) {
        UserEntity user = userRepositoryJPA.findByEmail(email);

        return UserConverter.convert(user);
    }

    @Override
    public UserD create(UserD user) {
        UserEntity userEntity = null;

        if(user.getTrader().isPresent()) {
            userEntity = UserEntity.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .trader(TraderToEntityConverter.convert(user.getTrader().get()))
                    .role(user.getRole())
                    .build();
        }
        else {
            userEntity = UserEntity.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        }

        return UserConverter.convert(userRepositoryJPA.save(userEntity));
    }

    // not implemented
//    @Override
//    public Boolean deleteUser(Long id) {
//        userRepositoryJPA.deleteById(id);
//        if(!userRepositoryJPA.existsById(id)) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
}
