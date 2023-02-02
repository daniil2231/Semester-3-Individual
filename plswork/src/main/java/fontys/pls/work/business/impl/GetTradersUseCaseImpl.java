package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTradersUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTradersUseCaseImpl implements GetTradersUseCase {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public List<Trader> getTraders() {
        return traderRepository.getTraders();
    }
}
