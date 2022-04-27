package pl.piasta.acmanagement.infrastructure.acunits;

import pl.piasta.acmanagement.domain.acunits.model.Devices;
import pl.piasta.acmanagement.domain.acunits.model.EnergyConsum;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AcUnitsRepository {

    Long add(AcUnit unit);
    boolean exists(Long id);
    Optional<AcUnit> get(Long id);
    List<AcUnit> getAll();

    List<EnergyConsum> getConsum(Date start, Date end);

     List<Devices> getDevices(String User, String type);

    Long addDevice(Devices devices);

}
