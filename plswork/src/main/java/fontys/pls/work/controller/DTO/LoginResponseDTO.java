package fontys.pls.work.controller.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginResponseDTO {
    private String accessToken;
    private String role;
}
