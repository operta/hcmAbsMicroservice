package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbRequestStatuses;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the AbRequestStatuses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbRequestStatusesRepository extends JpaRepository<AbRequestStatuses, Long> {
    List<AbRequestStatuses> findByIdRequestId(Long id);
}
