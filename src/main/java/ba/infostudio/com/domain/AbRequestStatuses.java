package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AbRequestStatuses.
 */
@Entity
@Table(name = "ab_request_statuses")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbRequestStatuses extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @OneToOne
    @JoinColumn(name = "id_status")
    private AbStatuses idStatus;

    @OneToOne
    @JoinColumn(name = "id_request")
    private AbRequests idRequest;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public AbRequestStatuses dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public AbStatuses getIdStatus() {
        return idStatus;
    }

    public AbRequestStatuses idStatus(AbStatuses abStatuses) {
        this.idStatus = abStatuses;
        return this;
    }

    public void setIdStatus(AbStatuses abStatuses) {
        this.idStatus = abStatuses;
    }

    public AbRequests getIdRequest() {
        return idRequest;
    }

    public AbRequestStatuses idRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
        return this;
    }

    public void setIdRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbRequestStatuses abRequestStatuses = (AbRequestStatuses) o;
        if (abRequestStatuses.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestStatuses.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestStatuses{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            "}";
    }
}
