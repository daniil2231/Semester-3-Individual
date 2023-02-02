package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTraderUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetTraderUseCaseImpl  implements GetTraderUseCase {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public Optional<Trader> getTrader(Long id) {
        return traderRepository.getTrader(id);
    }
}
