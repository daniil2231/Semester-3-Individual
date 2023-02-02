package fontys.pls.work.controller;

import fontys.pls.work.business.LoginUseCase;
import fontys.pls.work.controller.DTO.LoginResponseDTO;
import fontys.pls.work.controller.DTO.LoginResponseDTOConverter;
import fontys.pls.work.domain.UserD;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping()
    public LoginResponseDTO login(@RequestBody UserD user) {
        return LoginResponseDTOConverter.convert(loginUseCase.login(user.getEmail(), user.getPassword()));
    }
}
