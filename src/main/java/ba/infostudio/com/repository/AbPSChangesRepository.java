package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbPSChanges;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbPSChanges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbPSChangesRepository extends JpaRepository<AbPSChanges, Long> {

}
