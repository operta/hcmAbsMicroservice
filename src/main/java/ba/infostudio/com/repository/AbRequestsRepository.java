package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbRequests;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbRequests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbRequestsRepository extends JpaRepository<AbRequests, Long> {

}
