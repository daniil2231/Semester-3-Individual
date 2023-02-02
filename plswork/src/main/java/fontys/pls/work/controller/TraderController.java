package fontys.pls.work.controller;

import fontys.pls.work.business.*;
import fontys.pls.work.configuration.security.isauthenticated.IsAuthenticated;
import fontys.pls.work.controller.DTO.TotalTradedVolumeResponseDTO;
import fontys.pls.work.controller.DTO.TotalTradedVolumeResponseDTOConverter;
import fontys.pls.work.controller.DTO.TraderDTO;
import fontys.pls.work.controller.DTO.TraderDTOConverter;
import fontys.pls.work.domain.Trader;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/traders")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TraderController {
    private final CreateTraderUseCase createTraderUseCase;

    private final ChangeTraderFundsUseCase changeTraderFundsUseCase;

    private final GetTraderUseCase getTraderUseCase;

    private final GetTradersUseCase getTradersUseCase;

    private final UpdateTraderUseCase updateTraderUseCase;

    private final GetTotalTradedVolume getTotalTradedVolume;

    private final GetTradersByEmailUseCase getTradersByEmailUseCase;

    //private final DeleteTraderUseCase deleteTraderUseCase;

    @PostMapping()
    public TraderDTO createTrader(@RequestBody Trader trader) {
        return TraderDTOConverter.convert(createTraderUseCase.createTrader(trader.getEmail(), trader.getPassword(), trader.getNameOnCard(), trader.getCardNumber(), trader.getCardCVV(), trader.getCardValidThru()));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader"})
    @PutMapping("/update")
    public TraderDTO updateTrader(@RequestBody Trader trader) {
        return TraderDTOConverter.convert(updateTraderUseCase.updateTrader(trader.getId(), trader.getNameOnCard(), trader.getCardNumber(), trader.getCardCVV(), trader.getCardValidThru()));
    }

    @PutMapping()
    public TraderDTO addFundsToTrader(@RequestBody Trader trader) {
        return TraderDTOConverter.convert(changeTraderFundsUseCase.changeTraderFunds(trader.getFunds(), trader.getId()));
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_trader", "ROLE_admin"})
    @GetMapping("{id}")
    public ResponseEntity<TraderDTO> getTrader(@PathVariable(value = "id") final Long id) {
        Optional<TraderDTO> trader = getTraderUseCase.getTrader(id).map(TraderDTOConverter::convert);
        if(trader.isPresent()) {
            return ResponseEntity.ok(trader.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @GetMapping()
    public List<TraderDTO> getTraders() {
        return getTradersUseCase.getTraders()
                .stream()
                .map(TraderDTOConverter::convert)
                .toList();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @GetMapping("/filter/{email}")
    public List<TraderDTO> getTradersByEmail(@PathVariable String email) {
        return getTradersByEmailUseCase.getTradersByEmail(email)
                .stream()
                .map(TraderDTOConverter::convert)
                .toList();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_admin"})
    @GetMapping("/volume")
    public TotalTradedVolumeResponseDTO getTotalTradedVolume() {
        return TotalTradedVolumeResponseDTOConverter.convert(getTotalTradedVolume.getTotalTradedVolume());
    }

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_admin"})
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteTrader(@PathVariable(value = "id") final Long id) {
//        deleteTraderUseCase.deleteTrader(id);
//        return ResponseEntity.noContent().build();
//    }
}
