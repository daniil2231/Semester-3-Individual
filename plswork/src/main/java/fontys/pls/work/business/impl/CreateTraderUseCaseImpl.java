package fontys.pls.work.business.impl;

import fontys.pls.work.business.CreateTraderUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.domain.UserD;
import fontys.pls.work.persistence.TraderRepository;
import fontys.pls.work.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateTraderUseCaseImpl implements CreateTraderUseCase {

    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Transactional
    @Override
    public Trader createTrader(String email, String password, String nameOnCard, String cardNumber, String cardCVV, String cardValidThru) {
        String encodedPassword = passwordEncoder.encode(password);
        Trader traderDomain;

        traderDomain = Trader.builder()
                .email(email)
                .password(encodedPassword)
                .nameOnCard(nameOnCard)
                .cardNumber(cardNumber)
                .cardCVV(cardCVV)
                .cardValidThru(cardValidThru)
                .tradedVolume(0.0)
                .realizedPnl(0.0)
                .funds(0.0)
                .build();

        //traderRepository.createTrader(traderDomain);

        saveNewUser(traderDomain);

        return traderDomain;
    }

    private void saveNewUser(Trader trader) {
        UserD newUser = UserD.builder()
                .email(trader.getEmail())
                .password(trader.getPassword())
                .trader(Optional.of(trader))
                .role("trader")
                .build();
        userRepository.create(newUser);
    }
}
