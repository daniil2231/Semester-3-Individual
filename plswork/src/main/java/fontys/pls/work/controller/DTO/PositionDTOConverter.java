package fontys.pls.work.controller.DTO;

import fontys.pls.work.domain.Position;

public class PositionDTOConverter {
    private PositionDTOConverter() {
    }

    public static PositionDTO convert(Position position) {
        return PositionDTO.builder()
                .id(position.getId())
                .trader(TraderDTOConverter.convert(position.getTrader()))
                .positionType(position.getPositionType())
                .val(position.getVal())
                .entryPrice(position.getEntryPrice())
                .liquidationPrice(position.getLiquidationPrice())
                .changeInPrice(position.getChangeInPrice())
                .build();
    }
}
