package pl.piasta.acmanagement.domain.acunits;

import pl.piasta.acmanagement.domain.acunits.model.AcUnit;

import java.util.List;
import java.util.Optional;

public interface AcUnitsRepository {

    Long add(AcUnit unit);
    boolean exists(Long id);
    Optional<AcUnit> get(Long id);
    List<AcUnit> getAll();
}
