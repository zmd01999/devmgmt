package pl.piasta.acmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.domain.acsystems.model.JobDetails;
import pl.piasta.acmanagement.infrastructure.model.AcSystemsEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AcSystemsEntityMapper {

    private final AcUnitsEntityMapper acUnitsEntityMapper;
    private final CustomersEntityMapper customersEntityMapper;

    public Optional<AcSystemFull> mapToAcSystemFullOptional(Optional<AcSystemsEntity> acSystemsEntity) {
        return acSystemsEntity.map(this::mapToAcSystemFull);
    }

    public List<AcSystem> mapToAcSystemList(List<AcSystemsEntity> acSystemsEntityList) {
        return acSystemsEntityList
                .stream()
                .map(this::mapToAcSystem)
                .collect(Collectors.toList());
    }

    public JobDetails mapToJobDetails(AcSystemsEntity acSystemsEntity) {
        return new JobDetails(acSystemsEntity.getJobKey(), acSystemsEntity.getNextMaintainance(), acSystemsEntity.isNotifications());
    }

    private AcSystem mapToAcSystem(AcSystemsEntity acSystemsEntity) {
        return new AcSystem(
                acSystemsEntity.getId(),
                acSystemsEntity.getUnit().getId(),
                acSystemsEntity.getNextMaintainance(),
                acSystemsEntity.isNotifications(),
                acSystemsEntity.getCustomer().getId());
    }

    private AcSystemFull mapToAcSystemFull(AcSystemsEntity acSystemsEntity) {
        return new AcSystemFull(
                acSystemsEntity.getId(),
                acUnitsEntityMapper.mapToAcUnit(acSystemsEntity.getUnit()),
                acSystemsEntity.getNextMaintainance(),
                acSystemsEntity.isNotifications(),
                customersEntityMapper.mapToCustomer(acSystemsEntity.getCustomer()));
    }
}
