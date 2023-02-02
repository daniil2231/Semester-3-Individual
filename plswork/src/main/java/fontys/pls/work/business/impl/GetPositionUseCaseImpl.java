package fontys.pls.work.business.impl;

import fontys.pls.work.business.GetPositionUseCase;
import fontys.pls.work.domain.Position;
import fontys.pls.work.persistence.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetPositionUseCaseImpl implements GetPositionUseCase {

    @Autowired
    private PositionRepository repository;

    @Override
    public Optional<Position> getPosition(Long id) {
        return repository.getPosition(id);
    }
}
