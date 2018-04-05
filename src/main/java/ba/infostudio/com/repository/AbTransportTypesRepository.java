package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbTransportTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbTransportTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbTransportTypesRepository extends JpaRepository<AbTransportTypes, Long> {

}
