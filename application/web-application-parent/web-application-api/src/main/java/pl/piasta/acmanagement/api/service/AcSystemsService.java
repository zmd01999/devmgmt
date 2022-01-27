package pl.piasta.acmanagement.api.service;

import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;

import java.time.LocalDateTime;
import java.util.List;

public interface AcSystemsService {

    Long addSystem(AcSystem system);
    void removeSystemById(Long id);
    AcSystemFull getSystemById(Long id);
    List<AcSystem> getAllSystems();
    void setNextMaintainance(Long id, LocalDateTime date);
    void setNotifications(Long id, boolean enabled);
}
