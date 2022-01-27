package pl.piasta.acmanagement.infrastructure.service.lmpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.api.misc.MyException;
import pl.piasta.acmanagement.api.service.AcUnitsService;
import pl.piasta.acmanagement.infrastructure.acunits.AcUnitsRepository;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.domain.misc.ErrorCode;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AcUnitsServiceImpl implements AcUnitsService {

    private final AcUnitsRepository acUnitsRepository;

    @Override
    @Transactional
    public Long updateUnit(AcUnit unit) {
        return acUnitsRepository.add(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public AcUnit getUnitById(Long id) {
        return acUnitsRepository.get(id)
                .orElseThrow(() -> new MyException(ErrorCode.UNIT_NOT_EXISTS));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcUnit> getAllUnits() {
        return acUnitsRepository.getAll();
    }
}
