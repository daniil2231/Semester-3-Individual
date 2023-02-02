package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTickerPriceUseCase;
import fontys.pls.work.domain.TickerPrice;
import fontys.pls.work.persistence.TickerPriceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetTickerPriceUseCaseImpl implements GetTickerPriceUseCase {

    @Autowired
    private TickerPriceRepository tickerPriceRepository;

    @Override
    public Optional<TickerPrice> getTickerPrice() {
        return tickerPriceRepository.getCurrentPrice();
    }
}
