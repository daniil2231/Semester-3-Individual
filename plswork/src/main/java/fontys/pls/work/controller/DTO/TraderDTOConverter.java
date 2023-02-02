package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;

public class TraderDTOConverter {
    private TraderDTOConverter() {
    }

    public static TraderDTO convert(Trader trader) {
        return TraderDTO.builder()
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
