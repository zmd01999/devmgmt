package pl.piasta.acmanagement.api.service;

import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.domain.acunits.model.EnergyConsum;

import java.util.Date;
import java.util.List;

public interface AcUnitsService {

    Long updateUnit(AcUnit unit);
    AcUnit getUnitById(Long id);
    List<AcUnit> getAllUnits();

    List<EnergyConsum> getEnergyConsum(Date start, Date end);
}
