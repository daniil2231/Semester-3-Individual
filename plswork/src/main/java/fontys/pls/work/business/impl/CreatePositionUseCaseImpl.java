package fontys.pls.work.business.impl;

import fontys.pls.work.business.*;
import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.PositionRepository;
import fontys.pls.work.persistence.TickerPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreatePositionUseCaseImpl implements CreatePositionUseCase {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private GetTickerPriceUseCase getTickerPriceUseCase;

    @Autowired
    private ChangeTraderTradedVolumeUseCase changeTraderTradedVolumeUseCase;

    @Autowired
    private GetTraderUseCase getTraderUseCase;

    @Autowired
    private ChangeTraderFundsUseCase changeTraderFundsUseCase;

    @Override
    public Position createPosition(Long traderId, Double positionVal, String positionType) {
        Double currentPrice = 0.0;
        if(getTickerPriceUseCase.getTickerPrice().isPresent()) {
            currentPrice = getTickerPriceUseCase.getTickerPrice().get().getPrice();
        }
        else {
            throw new NoSuchElementException();
        }

        Optional<Trader> traderOptional = getTraderUseCase.getTrader(traderId);
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

        Position positionDomain;
        //Initial Margin Rate = 1/Leverage
        //Maintenance Margin Rate (MMR) = 0.005
        //FORMULA (LONG POSITIONS):
        //Liquidation Price = Entry Price * (1 - Initial Margin Rate + Maintenance Margin Rate)
        //Liq Price when leverage is 1 = Entry Price * Maintenance Margin Rate
        if(Objects.equals(positionType, "long")) {
                positionDomain = Position.builder()
                        .trader(trader)
                        .positionType(positionType)
                        .val(positionVal)
                        .entryPrice(currentPrice)
                        .liquidationPrice(currentPrice * 0.005)
                        .build();
        }
        //FORMULA (SHORT POSITIONS):
        //Liquidation Price = Entry Price * (1 + Initial Margin Rate - Maintenance Margin Rate)
        //Liq Price when leverage is 1 = Entry Price * Maintenance Margin Rate
        else {
                positionDomain = Position.builder()
                        .trader(trader)
                        .positionType(positionType)
                        .val(positionVal)
                        .entryPrice(currentPrice)
                        .liquidationPrice(currentPrice * (1 + 1 - 0.005))
                        .build();
        }
        changeTraderTradedVolumeUseCase.changeTraderTradedVolume(positionVal, trader.getId());
        changeTraderFundsUseCase.changeTraderFunds(-positionVal, trader.getId());

        return positionRepository.createPosition(positionDomain);
    }
}
