package fontys.pls.work.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Trader {
    private Long id;

    private String email;

    private String password;

    private String nameOnCard;

    private String cardNumber;

    private String cardCVV;

    private String cardValidThru;

    private Double tradedVolume;

    private Double realizedPnl;

    private Double funds;
}
