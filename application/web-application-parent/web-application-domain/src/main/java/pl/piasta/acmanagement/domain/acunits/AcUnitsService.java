package pl.piasta.acmanagement.domain.acunits;

import pl.piasta.acmanagement.domain.acunits.model.AcUnit;

import java.util.List;

public interface AcUnitsService {

    Long updateUnit(AcUnit unit);
    AcUnit getUnitById(Long id);
    List<AcUnit> getAllUnits();
}
