package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbVacationLeaveDays;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbVacationLeaveDays entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbVacationLeaveDaysRepository extends JpaRepository<AbVacationLeaveDays, Long> {
    List<AbVacationLeaveDays> findByIdEmployeeId(Long id);
}
