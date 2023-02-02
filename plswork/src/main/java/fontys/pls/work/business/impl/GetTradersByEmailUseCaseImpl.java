package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTradersByEmailUseCase;
import fontys.pls.work.domain.Trader;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTradersByEmailUseCaseImpl implements GetTradersByEmailUseCase {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public List<Trader> getTradersByEmail(String email) {
        return traderRepository.getTradersByEmail(email);
    }
}
