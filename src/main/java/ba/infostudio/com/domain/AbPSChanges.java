package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AbPSChanges.
 */
@Entity
@Table(name = "ab_permitted_status_changes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbPSChanges extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_absence_type")
    private AbAbsenceTypes idAbsenceType;

    @OneToOne
    @JoinColumn(name= "id_status_from")
    private AbStatuses idStatusFrom;

    @OneToOne
    @JoinColumn(name = "id_status_to")
    private AbStatuses idStatusTo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public AbPSChanges description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbAbsenceTypes getIdAbsenceType() {
        return idAbsenceType;
    }

    public AbPSChanges idAbsenceType(AbAbsenceTypes abAbsenceTypes) {
        this.idAbsenceType = abAbsenceTypes;
        return this;
    }

    public void setIdAbsenceType(AbAbsenceTypes abAbsenceTypes) {
        this.idAbsenceType = abAbsenceTypes;
    }

    public AbStatuses getIdStatusFrom() {
        return idStatusFrom;
    }

    public AbPSChanges idStatusFrom(AbStatuses abStatuses) {
        this.idStatusFrom = abStatuses;
        return this;
    }

    public void setIdStatusFrom(AbStatuses abStatuses) {
        this.idStatusFrom = abStatuses;
    }

    public AbStatuses getIdStatusTo() {
        return idStatusTo;
    }

    public AbPSChanges idStatusTo(AbStatuses abStatuses) {
        this.idStatusTo = abStatuses;
        return this;
    }

    public void setIdStatusTo(AbStatuses abStatuses) {
        this.idStatusTo = abStatuses;
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
        AbPSChanges abPSChanges = (AbPSChanges) o;
        if (abPSChanges.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abPSChanges.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbPSChanges{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
