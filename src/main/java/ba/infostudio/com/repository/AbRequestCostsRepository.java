package ba.infostudio.com.repository;

import ba.infostudio.com.domain.AbRequestCosts;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AbRequestCosts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbRequestCostsRepository extends JpaRepository<AbRequestCosts, Long> {
    List<AbRequestCosts> findByIdRequestId(Long id);
}
