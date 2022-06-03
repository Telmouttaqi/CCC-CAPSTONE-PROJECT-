package ccc.data;

import ccc.models.Performances;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PerformancesRepository {

    List<Performances> findAll();

    @Transactional
    Performances findById(int performanceId);

    Performances addPerformance(Performances performance);

    Boolean update(Performances performance);

    Boolean deleteById(int performanceId);

}
