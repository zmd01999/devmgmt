package pl.piasta.acmanagement.infrastructure.acsystems;

import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;

public interface AcDetailRepository {
    Long add(AcDetail acDetail);
    AcDetail findByid(Long id);
}
