package fontys.pls.work.persistence.converters;

import fontys.pls.work.domain.Position;
import fontys.pls.work.persistence.Entity.PositionEntity;

public class PositionConverter {
    private PositionConverter() {
    }

    public static Position convert(PositionEntity position) {
        return Position.builder()
                .id(position.getId())
                .trader(TraderConverter.convert(position.getTrader()))
                .positionType(position.getPositionType())
                .val(position.getVal())
                .entryPrice(position.getEntryPrice())
                .liquidationPrice(position.getLiquidationPrice())
                .changeInPrice(position.getChangeInPrice())
                .build();
    }
}
