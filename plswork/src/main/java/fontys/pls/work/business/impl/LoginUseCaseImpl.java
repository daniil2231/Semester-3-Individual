package fontys.pls.work.business.impl;

import fontys.pls.work.business.AccessTokenEncoder;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.business.LoginUseCase;
import fontys.pls.work.business.exception.InvalidCredentialsException;
import fontys.pls.work.domain.AccessToken;
import fontys.pls.work.domain.LoginResponse;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.Entity.UserEntity;
import fontys.pls.work.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    private PasswordEncoder passwordEncoder;

    private AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(String email, String password) {
        UserD userDomain = userRepository.login(email, password);

        if (userDomain == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(password, userDomain.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(userDomain);
        return LoginResponse.builder().accessToken(accessToken).role(userDomain.getRole()).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserD user) {

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());

        if(user.getTrader() != null && user.getTrader().isPresent()) {
            return accessTokenEncoder.encode(
                    AccessToken.builder()
                            .subject(user.getEmail())
                            .roles(roles)
                            .id(user.getTrader().get().getId())
                            .build());
        }
        else {
            return accessTokenEncoder.encode(
                    AccessToken.builder()
                            .subject(user.getEmail())
                            .roles(roles)
                            .id(user.getId())
                            .build());
        }
    }
}
