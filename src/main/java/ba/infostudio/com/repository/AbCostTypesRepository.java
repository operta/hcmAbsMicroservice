package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbCostTypes;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbCostTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbCostTypesRepository extends JpaRepository<AbCostTypes, Long> {

}
