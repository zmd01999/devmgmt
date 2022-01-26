package pl.piasta.acmanagement.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.piasta.acmanagement.api.acunits.model.UpdateUnitRequest;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.dto.acunits.AcUnitResponse;

import java.util.List;

@Mapper
public interface AcUnitMapper {

    AcUnit mapToAcUnit(UpdateUnitRequest request);
    AcUnit mapToAcUnit(AcUnitResponse response);
    AcUnitResponse mapToResponse(AcUnit unit);
    List<AcUnitResponse> mapToResponseList(List<AcUnit> unitList);
    UpdateUnitRequest mapToRequest(AcUnitResponse response);

    @Mapping(target = "id", ignore = true)
    void update(UpdateUnitRequest request, @MappingTarget AcUnit unit);
}
