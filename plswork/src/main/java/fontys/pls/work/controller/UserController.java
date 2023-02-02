package fontys.pls.work.controller;

import fontys.pls.work.business.CreateUserUseCase;
import fontys.pls.work.controller.DTO.UserDTO;
import fontys.pls.work.controller.DTO.UserDTOConverter;
import fontys.pls.work.domain.UserD;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    //private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping()
    public UserDTO create(@RequestBody UserD user) {
        return UserDTOConverter.convert(createUserUseCase.createUser(user.getEmail(), user.getPassword()));
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_admin"})
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") final Long id) {
//        deleteUserUseCase.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
}
