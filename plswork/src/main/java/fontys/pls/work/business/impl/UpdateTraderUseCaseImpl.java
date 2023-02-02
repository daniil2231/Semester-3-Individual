package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.business.UpdateTraderUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateTraderUseCaseImpl implements UpdateTraderUseCase {

    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    // doesnt want to update with an encoded password for some reason
    @Override
    public Trader updateTrader(Long id, String nameOnCard, String cardNumber, String cardCVV, String cardValidThru) {
        Optional<Trader> traderOptional = getTraderUseCase.getTrader(id);

        Trader trader = null;

        if(traderOptional.isPresent()) {
            trader = Trader.builder()
                    .id(traderOptional.get().getId())
                    .email(traderOptional.get().getEmail())
                    .password(traderOptional.get().getPassword())
                    .nameOnCard(nameOnCard)
                    .cardNumber(cardNumber)
                    .cardCVV(cardCVV)
                    .cardValidThru(cardValidThru)
                    .funds(traderOptional.get().getFunds())
                    .realizedPnl(traderOptional.get().getRealizedPnl())
                    .tradedVolume(traderOptional.get().getTradedVolume())
                    .build();
        }
        else {
            throw new NoSuchElementException();
        }

        return traderRepository.updateTrader(trader);
    }
}
