package fontys.pls.work.persistence.impl;

import fontys.pls.work.domain.TotalTradedVolumeResponse;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.TraderEntity;
import fontys.pls.work.persistence.TraderRepository;
import fontys.pls.work.persistence.TraderRepositoryJPA;
import fontys.pls.work.persistence.converters.PositionConverter;
import fontys.pls.work.persistence.converters.TraderConverter;
import fontys.pls.work.persistence.converters.TraderToEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TraderRepositoryImpl implements TraderRepository {

    private TraderRepositoryJPA jpaRepo;

    @Override
    public Trader createTrader(Trader traderDomain) {
        TraderEntity traderEntity = TraderEntity.builder()
                .email(traderDomain.getEmail())
                .password(traderDomain.getPassword())
                .nameOnCard(traderDomain.getNameOnCard())
                .cardNumber(traderDomain.getCardNumber())
                .cardCVV(traderDomain.getCardCVV())
                .cardValidThru(traderDomain.getCardValidThru())
                .tradedVolume(traderDomain.getTradedVolume())
                .realizedPnl(traderDomain.getRealizedPnl())
                .funds(traderDomain.getFunds())
                .build();

        return TraderConverter.convert(jpaRepo.save(traderEntity));
    }

    @Override
    public Trader updateTrader(Trader trader) {
        TraderEntity traderToSave = TraderEntity.builder()
                .id(trader.getId())
                .email(trader.getEmail())
                .tradedVolume(trader.getTradedVolume())
                .funds(trader.getFunds())
                .realizedPnl(trader.getRealizedPnl())
                .cardNumber(trader.getCardNumber())
                .cardValidThru(trader.getCardValidThru())
                .cardCVV(trader.getCardCVV())
                .funds(trader.getFunds())
                .password(trader.getPassword())
                .nameOnCard(trader.getNameOnCard())
                .build();

        return TraderConverter.convert(jpaRepo.save(traderToSave));
    }

    @Override
    public Optional<Trader> getTrader(Long id) {
        return jpaRepo.findById(id).map(TraderConverter::convert);
    }

    @Override
    public List<Trader> getTraders() {
        return jpaRepo.findAll()
                .stream()
                .map(TraderConverter::convert)
                .toList();
    }

    @Override
    public List<Trader> getTradersByEmail(String email) {
        List<Trader> traders = new ArrayList<>();

        for (TraderEntity t : jpaRepo.findAll()) {
            if(t.getEmail().toLowerCase().contains(email.toLowerCase())){
                traders.add(TraderConverter.convert(t));
            }
        }

        return traders;
    }

    // not implemented
//    @Override
//    public Boolean deleteTrader(Long id) {
//        jpaRepo.deleteById(id);
//        if(!jpaRepo.existsById(id)) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    @Override
    public Trader changeTraderFundsPnlVolume(Trader trader) {
        TraderEntity traderToSave = TraderEntity.builder()
                .id(trader.getId())
                .email(trader.getEmail())
                .tradedVolume(trader.getTradedVolume())
                .funds(trader.getFunds())
                .realizedPnl(trader.getRealizedPnl())
                .cardNumber(trader.getCardNumber())
                .cardValidThru(trader.getCardValidThru())
                .cardCVV(trader.getCardCVV())
                .funds(trader.getFunds())
                .password(trader.getPassword())
                .nameOnCard(trader.getNameOnCard())
                .build();

        return TraderConverter.convert(jpaRepo.save(traderToSave));
    }

    @Override
    public TotalTradedVolumeResponse sumTotalTradedVolume() {
        return TotalTradedVolumeResponse.builder().totalTradedVolume(jpaRepo.sumTotalVolume()).build();
    }
}
