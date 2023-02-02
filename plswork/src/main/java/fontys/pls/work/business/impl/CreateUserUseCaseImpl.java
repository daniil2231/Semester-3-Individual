package fontys.pls.work.business.impl;

import fontys.pls.work.business.CreateUserUseCase;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserD createUser(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        UserD user;

        user = UserD.builder()
                .email(email)
                .trader(java.util.Optional.empty())
                .password(encodedPassword)
                .role("admin")
                .build();

        return userRepository.create(user);
    }
}
