package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbStatuses;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbStatuses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbStatusesRepository extends JpaRepository<AbStatuses, Long> {

}
