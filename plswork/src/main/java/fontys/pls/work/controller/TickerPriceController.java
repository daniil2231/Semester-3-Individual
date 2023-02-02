package fontys.pls.work.controller;

import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.controller.DTO.TickerPriceDTO;
import fontys.pls.work.controller.DTO.TickerPriceDTOConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/tickers/prices")
@CrossOrigin(origins = "http://localhost:3000")
public class TickerPriceController {
    private final GetTickerPriceUseCase getTickerPriceUseCase;

    public TickerPriceController(GetTickerPriceUseCase getTickerPriceUseCase) {
        this.getTickerPriceUseCase = getTickerPriceUseCase;
    }

    @GetMapping
    public TickerPriceDTO getTickerPrice() {
        Optional<TickerPriceDTO> price = getTickerPriceUseCase.getTickerPrice().map(TickerPriceDTOConverter::convert);
        return price.get();
    }
}
