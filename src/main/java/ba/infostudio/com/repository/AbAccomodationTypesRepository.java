package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbAccomodationTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbAccomodationTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbAccomodationTypesRepository extends JpaRepository<AbAccomodationTypes, Long> {

}
