package fontys.pls.work.controller.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TraderDTO {
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
