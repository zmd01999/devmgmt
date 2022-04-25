package pl.piasta.acmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.acunits.model.Devices;
import pl.piasta.acmanagement.domain.acunits.model.EnergyConsum;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;
import pl.piasta.acmanagement.infrastructure.model.DeviceEntity;
import pl.piasta.acmanagement.infrastructure.model.EnergyConsumEntity;

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

    public List<EnergyConsum> mapToEnergyCList(List<EnergyConsumEntity> energyConsumEntityList) {
        return energyConsumEntityList
                .stream()
                .map(this::mapToEnergy)
                .collect(Collectors.toList());
    }

    public List<Devices> mapToDevicesList(List<DeviceEntity> deviceEntities) {
        return deviceEntities
                .stream()
                .map(this::mapToDevice)
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

    public EnergyConsum mapToEnergy(EnergyConsumEntity energyConsumEntity) {
        return new EnergyConsum(
                energyConsumEntity.getId(),
                energyConsumEntity.getScene(),
                energyConsumEntity.getSeam(),
                energyConsumEntity.getProduct(),
                energyConsumEntity.getPowerConsum(),
                energyConsumEntity.getSmartConsum(),
                energyConsumEntity.getTradConsum(),
                energyConsumEntity.getDate());
    }

    public Devices mapToDevice(DeviceEntity deviceEntity) {
        return new Devices(
                deviceEntity.getId(),
                deviceEntity.getType(),
                deviceEntity.getUserName(),
                deviceEntity.getNikeName(),
                deviceEntity.getStatus());

    }
}
