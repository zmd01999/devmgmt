package pl.piasta.acmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AcUnitsEntityMapper {

    public Optional<AcUnit> mapToAcUnitOptional(Optional<AcUnitsEntity> acUnitsEntity) {
        return acUnitsEntity.map(this::mapToAcUnit);
    }

    public List<AcUnit> mapToAcUnitList(List<AcUnitsEntity> acUnitsEntityList) {
        return acUnitsEntityList
                .stream()
                .map(this::mapToAcUnit)
                .collect(Collectors.toList());
    }

    public AcUnit mapToAcUnit(AcUnitsEntity acUnitsEntity) {
        return new AcUnit(
                acUnitsEntity.getId(),
                acUnitsEntity.getManufacturer(),
                acUnitsEntity.getProductName(),
                acUnitsEntity.getVoltage(),
                acUnitsEntity.getCurrent());
    }
}
