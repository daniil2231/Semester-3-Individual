package fontys.pls.work.controller.DTO;

import lombok.*;

import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO {
    private Long id;

    private TraderDTO trader;

    private String email;

    private String password;

    private String role;
}
