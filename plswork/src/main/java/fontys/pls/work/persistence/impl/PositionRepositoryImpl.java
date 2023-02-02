package fontys.pls.work.persistence.impl;

import fontys.pls.work.domain.Position;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.Entity.PositionEntity;
import fontys.pls.work.persistence.Entity.TraderEntity;
import fontys.pls.work.persistence.PositionRepository;
import fontys.pls.work.persistence.PositionRepositoryJPA;
import fontys.pls.work.persistence.converters.PositionConverter;
import fontys.pls.work.persistence.converters.PositionToEntityConverter;
import fontys.pls.work.persistence.converters.TraderConverter;
import fontys.pls.work.persistence.converters.TraderToEntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private PositionRepositoryJPA jpaRepo;

    @Override
    public List<Position> getPositions() {
        List<Position> positions = jpaRepo.findAll()
                .stream()
                .map(PositionConverter::convert)
                .toList();

        return positions;
    }

    @Override
    public List<Position> getPositionsByTraders_id(Trader trader) {
        List<Position> positions = jpaRepo.findAllByTrader(TraderToEntityConverter.convert(trader))
                .stream()
                .map(PositionConverter::convert)
                .toList();

        return positions;
    }

    public Optional<Position> getPosition(Long id) {
        return jpaRepo.findById(id).map(PositionConverter::convert);
    }

    @Override
    public Position createPosition(Position positionDomain){

        PositionEntity positionEntity = PositionEntity.builder()
                .trader(TraderToEntityConverter.convert(positionDomain.getTrader()))
                .positionType(positionDomain.getPositionType())
                .val(positionDomain.getVal())
                .entryPrice(positionDomain.getEntryPrice())
                .liquidationPrice(positionDomain.getLiquidationPrice())
                .build();

        return PositionConverter.convert(jpaRepo.save(positionEntity));
    }

    @Override
    public Boolean closePosition(Long id) {

        jpaRepo.deleteById(id);
        if(!jpaRepo.existsById(id)) {
            return true;
        }
        else {
            return false;
        }
    }
}
