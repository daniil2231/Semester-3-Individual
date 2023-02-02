package fontys.pls.work.business.impl;

import fontys.pls.work.business.ChangeTraderFundsUseCase;
import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChangeTraderFundsUseCaseImpl implements ChangeTraderFundsUseCase {
    @Autowired
    private TraderRepository traderRepository;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    @Override
    public Trader changeTraderFunds(Double amountToAdd, Long id) {
        Optional<Trader> traderOptional = getTraderUseCase.getTrader(id);

        Trader trader = null;

        if(traderOptional.isPresent()) {
            trader = Trader.builder()
                    .id(traderOptional.get().getId())
                    .email(traderOptional.get().getEmail())
                    .password(traderOptional.get().getPassword())
                    .nameOnCard(traderOptional.get().getNameOnCard())
                    .cardNumber(traderOptional.get().getCardNumber())
                    .cardCVV(traderOptional.get().getCardCVV())
                    .cardValidThru(traderOptional.get().getCardValidThru())
                    .funds(traderOptional.get().getFunds())
                    .realizedPnl(traderOptional.get().getRealizedPnl())
                    .tradedVolume(traderOptional.get().getTradedVolume())
                    .build();
        }
        else {
            throw new NoSuchElementException();
        }

        Double newAmountOfFunds = trader.getFunds() + amountToAdd;

        trader.setFunds(newAmountOfFunds);

        return traderRepository.changeTraderFundsPnlVolume(trader);
    }
}
