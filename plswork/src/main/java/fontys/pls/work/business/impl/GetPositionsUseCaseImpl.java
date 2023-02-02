package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetPositionsUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.persistence.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetPositionsUseCaseImpl implements GetPositionsUseCase {

    @Autowired
    private PositionRepository repository;

    @Override
    public List<Position> getPositions() {
        return repository.getPositions();
    }
}
