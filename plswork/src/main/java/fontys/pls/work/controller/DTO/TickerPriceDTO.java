package fontys.pls.work.controller.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TickerPriceDTO {
    private Double price;
    private String symbol;
}
