package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbAbsenceTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbAbsenceTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbAbsenceTypesRepository extends JpaRepository<AbAbsenceTypes, Long> {

}
