package fontys.pls.work.business;

import fontys.pls.work.domain.UserD;

public interface CreateUserUseCase {
    UserD createUser(String email, String password);
}
