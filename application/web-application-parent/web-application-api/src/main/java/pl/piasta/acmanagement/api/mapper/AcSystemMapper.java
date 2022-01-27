package pl.piasta.acmanagement.api.mapper;

import org.mapstruct.Mapper;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.dto.acsystems.AcSystemFullResponse;
import pl.piasta.acmanagement.dto.acsystems.AcSystemResponse;
import pl.piasta.acmanagement.dto.acsystems.AddSystemRequest;

import java.util.List;

@Mapper
public interface AcSystemMapper {

    AcSystem mapToAcSystem(AddSystemRequest request);
    AcSystemFullResponse mapToFullResponse(AcSystemFull system);
    List<AcSystemResponse> mapToResponseList(List<AcSystem> systemList);
}
