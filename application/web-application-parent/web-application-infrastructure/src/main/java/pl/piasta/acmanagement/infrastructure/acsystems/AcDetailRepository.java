package pl.piasta.acmanagement.infrastructure.acsystems;

import org.springframework.beans.factory.annotation.Autowired;
import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.infrastructure.dao.AcDetailDao;

public interface AcDetailRepository {
    Long add(AcDetail acDetail);
}
