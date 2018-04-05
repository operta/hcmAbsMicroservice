package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbTravelPurposes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbTravelPurposes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbTravelPurposesRepository extends JpaRepository<AbTravelPurposes, Long> {

}
