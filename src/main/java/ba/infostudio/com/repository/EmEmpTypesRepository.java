package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmEmpTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EmEmpTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmEmpTypesRepository extends JpaRepository<EmEmpTypes, Long> {

}
