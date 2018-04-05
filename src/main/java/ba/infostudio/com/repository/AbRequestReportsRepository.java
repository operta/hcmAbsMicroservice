package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbRequestReports;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbRequestReports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbRequestReportsRepository extends JpaRepository<AbRequestReports, Long> {

}
