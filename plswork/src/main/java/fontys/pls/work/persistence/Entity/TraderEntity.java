package fontys.pls.work.persistence.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "traders")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TraderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "name_on_card")
    private String nameOnCard;

    @NotNull
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Column(name = "card_cvv")
    private String cardCVV;

    @NotNull
    @Column(name = "card_valid_thru")
    private String cardValidThru;

    @NotNull
    @Column(name = "traded_volume")
    private Double tradedVolume;

    @NotNull
    @Column(name = "realized_pnl")
    private Double realizedPnl;

    @NotNull
    @Column(name = "funds")
    private Double funds;
}
