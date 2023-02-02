package fontys.pls.work.persistence;

import fontys.pls.work.domain.UserD;

public interface UserRepository {
    UserD login(String email, String password);

    UserD create(UserD user);

    //Boolean deleteUser(Long id);
}
