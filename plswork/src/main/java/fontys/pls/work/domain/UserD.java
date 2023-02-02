package fontys.pls.work.domain;

import lombok.*;

import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserD {
    private Long id;

    private Optional<Trader> trader;

    private String email;

    private String password;

    private String role;
}
