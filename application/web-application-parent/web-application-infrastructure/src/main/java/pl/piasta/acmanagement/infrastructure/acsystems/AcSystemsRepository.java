package pl.piasta.acmanagement.infrastructure.acsystems;

import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.domain.acsystems.model.JobDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AcSystemsRepository {

    Long add(AcSystem unit, String jobKey);
    Optional<String> remove(Long id);
    Optional<JobDetails> updateNextMaintainance(Long id, LocalDateTime date);
    Optional<JobDetails> updateNotificationsStatus(Long id, boolean enabled);
    Optional<AcSystemFull> get(Long id);
    List<AcSystem> getAll();
}
