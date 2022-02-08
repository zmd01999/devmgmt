package pl.piasta.acmanagement.infrastructure.acsystems;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;
import pl.piasta.acmanagement.infrastructure.dao.AcDetailDao;
import pl.piasta.acmanagement.infrastructure.model.AcDetailEntity;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AcDetailRepositorylmpl implements AcDetailRepository {


    private final AcDetailDao acDetailDao;

    @Override
    @Transactional
    public Long add(AcDetail acDetail) {
        AcDetailEntity acDetailEntity = new AcDetailEntity();
        BeanUtils.copyProperties(acDetail, acDetailEntity);
        return acDetailDao.save(acDetailEntity).getId();
    }

    @Override
    public AcDetail findByid(Long id) {
        AcDetailEntity acDetailEntity = acDetailDao.findById(id).orElse(null);
        AcDetail acDetail = new AcDetail();
        if(acDetailEntity != null)
        BeanUtils.copyProperties(acDetailEntity, acDetail);
        return acDetail;
    }
}
