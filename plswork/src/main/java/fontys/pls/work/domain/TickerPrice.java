package fontys.pls.work.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TickerPrice {
    private Double price;
    private String symbol;
}
