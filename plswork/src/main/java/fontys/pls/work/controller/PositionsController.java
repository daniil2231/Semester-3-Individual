package fontys.pls.work.controller;

import fontys.pls.work.business.*;
import fontys.pls.work.configuration.security.isauthenticated.IsAuthenticated;
import fontys.pls.work.controller.DTO.PositionDTO;
import fontys.pls.work.controller.DTO.PositionDTOConverter;
import fontys.pls.work.domain.Position;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PositionsController {

    private GetPositionsByTraders_idUseCase getPositionsByTrader;

    private GetPositionUseCase getPositionUseCase;

    private CreatePositionUseCase createPositionUseCase;

    private ClosePositionUseCase closePositionUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader", "ROLE_admin"})
    @GetMapping("/all/{id}")
    public List<PositionDTO> getPositionsByTrader(@PathVariable(value = "id") final Long id) {
        return getPositionsByTrader.getPositionsByTraders_id(id)
                .stream()
                .map(PositionDTOConverter::convert)
                .toList();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader", "ROLE_admin"})
    @GetMapping("{id}")
    public ResponseEntity<PositionDTO> getPosition(@PathVariable(value = "id") final Long id) {
        Optional<Position> optionalPosition = getPositionUseCase.getPosition(id);
        if(optionalPosition.isPresent()) {
            return ResponseEntity.ok(getPositionUseCase.getPosition(id).map(PositionDTOConverter::convert).get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader"})
    @PostMapping()
    public ResponseEntity<PositionDTO> createPosition(@RequestBody Position position) {
        return ResponseEntity.ok(PositionDTOConverter.convert(createPositionUseCase.createPosition(position.getTrader().getId(), position.getVal(), position.getPositionType())));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader"})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> closePosition(@PathVariable(value = "id") final Long id) {
        closePositionUseCase.closePosition(id);
        return ResponseEntity.noContent().build();
    }
}
