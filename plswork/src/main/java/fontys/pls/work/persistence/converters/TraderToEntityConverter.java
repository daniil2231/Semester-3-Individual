package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;

public class TraderToEntityConverter {
    private TraderToEntityConverter() {
    }

    public static TraderEntity convert(Trader trader) {
        return TraderEntity.builder()
                .id(trader.getId())
                .email(trader.getEmail())
                .password(trader.getPassword())
                .nameOnCard(trader.getNameOnCard())
                .cardNumber(trader.getCardNumber())
                .cardCVV(trader.getCardCVV())
                .cardValidThru(trader.getCardValidThru())
                .tradedVolume(trader.getTradedVolume())
                .realizedPnl(trader.getRealizedPnl())
                .funds(trader.getFunds())
                .build();
    }
}
