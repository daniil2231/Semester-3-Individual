package fontys.pls.work.controller.DTO;

import fontys.pls.work.persistence.Entity.TraderEntity;
import lombok.*;

import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PositionDTO {
    private Long id;

    private TraderDTO trader;

    private String positionType;

    private Double val;

    private Double entryPrice;

    private Double liquidationPrice;

    private Double changeInPrice;
}
