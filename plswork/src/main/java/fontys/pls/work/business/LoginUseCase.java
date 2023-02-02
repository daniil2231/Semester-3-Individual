package fontys.pls.work.business;

import fontys.pls.work.domain.LoginResponse;
import fontys.pls.work.domain.UserD;

public interface LoginUseCase {
    LoginResponse login(String email, String password);
}
