package fontys.pls.work.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Builder
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BinanceTickerPriceEntity {
    private String symbol;
    private Double price;
}
