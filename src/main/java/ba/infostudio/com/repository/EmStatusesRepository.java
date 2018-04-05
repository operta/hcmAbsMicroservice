package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmStatuses;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EmStatuses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmStatusesRepository extends JpaRepository<EmStatuses, Long> {

}
