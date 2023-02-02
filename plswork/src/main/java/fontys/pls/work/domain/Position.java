package fontys.pls.work.domain;

import fontys.pls.work.persistence.Entity.TraderEntity;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    private Long id;

    private Trader trader;

    private String positionType;

    private Double val;

    private Double entryPrice;

    private Double liquidationPrice;

    private Double changeInPrice;
}
