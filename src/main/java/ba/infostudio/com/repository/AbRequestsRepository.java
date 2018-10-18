package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbRequests;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data JPA repository for the AbRequests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbRequestsRepository extends JpaRepository<AbRequests, Long> {
    List<AbRequests> findByIdEmployeeId(Long id);
    List<AbRequests> findByIdEmployeeIdAndYear(Long id, Integer year);
    List<AbRequests> findByDateFromGreaterThanEqual(LocalDate dateFrom);
    List<AbRequests> findByDateToLessThanEqual(LocalDate dateTo);
    List<AbRequests> findByDateFromLessThanEqualAndDateToGreaterThanEqual(LocalDate date1, LocalDate date2);
    List<AbRequests> findByIdAbsenceTypeId(Long id);
    List<AbRequests> findByIdStatusId(Long id);
    List<AbRequests> findById(Long id);


}
