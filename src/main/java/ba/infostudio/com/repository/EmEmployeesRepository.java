package ba.infostudio.com.repository;

import ba.infostudio.com.domain.EmEmployees;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the EmEmployees entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmEmployeesRepository extends JpaRepository<EmEmployees, Long> {

}
