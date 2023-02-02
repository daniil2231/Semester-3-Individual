package fontys.pls.work.persistence.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(name = "positions")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "traders_id")
    private TraderEntity trader;

    @NotBlank
    @Length(min = 1)
    @Column(name = "position_type")
    private String positionType;

    @NotNull
    @Column(name = "val")
    private Double val;

    @NotNull
    @Column(name = "entry_price")
    private Double entryPrice;

    @NotNull
    @Column(name = "liquidation_price")
    private Double liquidationPrice;

    @Column(name = "change_in_price")
    private Double changeInPrice;
}
