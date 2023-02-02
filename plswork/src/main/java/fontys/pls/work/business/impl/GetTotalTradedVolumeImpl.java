package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetTotalTradedVolume;
import fontys.pls.work.domain.TotalTradedVolumeResponse;
import fontys.pls.work.persistence.TraderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTotalTradedVolumeImpl implements GetTotalTradedVolume {

    @Autowired
    private TraderRepository traderRepository;

    @Override
    public TotalTradedVolumeResponse getTotalTradedVolume() {
        return traderRepository.sumTotalTradedVolume();
    }
}
