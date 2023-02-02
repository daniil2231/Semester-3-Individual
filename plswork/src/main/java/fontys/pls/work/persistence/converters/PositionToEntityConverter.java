package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Position;
import fontys.pls.work.persistence.Entity.PositionEntity;

public class PositionToEntityConverter {
    private PositionToEntityConverter() {
    }

    public static PositionEntity convert(Position position) {
        return PositionEntity.builder()
                .id(position.getId())
                .trader(TraderToEntityConverter.convert(position.getTrader()))
                .positionType(position.getPositionType())
                .val(position.getVal())
                .entryPrice(position.getEntryPrice())
                .liquidationPrice(position.getLiquidationPrice())
                .changeInPrice(position.getChangeInPrice())
                .build();
    }
}
