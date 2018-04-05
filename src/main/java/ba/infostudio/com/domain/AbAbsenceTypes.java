package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AbAbsenceTypes.
 */
@Entity
@Table(name = "ab_absence_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbAbsenceTypes extends AbstractAuditingEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    // @Column(name = "created_by")
    // private String createdBy;

    // @Column(name = "created_at")
    // private Instant createdAt;

    // @Column(name = "updated_by")
    // private String updatedBy;

    // @Column(name = "updated_at")
    // private Instant updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public AbAbsenceTypes code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AbAbsenceTypes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AbAbsenceTypes description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public String getCreatedBy() {
    //     return createdBy;
    // }

    // public AbAbsenceTypes createdBy(String createdBy) {
    //     this.createdBy = createdBy;
    //     return this;
    // }

    // public void setCreatedBy(String createdBy) {
    //     this.createdBy = createdBy;
    // }

    // public Instant getCreatedAt() {
    //     return createdAt;
    // }

    // public AbAbsenceTypes createdAt(Instant createdAt) {
    //     this.createdAt = createdAt;
    //     return this;
    // }

    // public void setCreatedAt(Instant createdAt) {
    //     this.createdAt = createdAt;
    // }

    // public String getUpdatedBy() {
    //     return updatedBy;
    // }

    // public AbAbsenceTypes updatedBy(String updatedBy) {
    //     this.updatedBy = updatedBy;
    //     return this;
    // }

    // public void setUpdatedBy(String updatedBy) {
    //     this.updatedBy = updatedBy;
    // }

    // public Instant getUpdatedAt() {
    //     return updatedAt;
    // }

    // public AbAbsenceTypes updatedAt(Instant updatedAt) {
    //     this.updatedAt = updatedAt;
    //     return this;
    // }

    // public void setUpdatedAt(Instant updatedAt) {
    //     this.updatedAt = updatedAt;
    // }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbAbsenceTypes abAbsenceTypes = (AbAbsenceTypes) o;
        if (abAbsenceTypes.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abAbsenceTypes.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbAbsenceTypes{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            // ", createdBy='" + getCreatedBy() + "'" +
            // ", createdAt='" + getCreatedAt() + "'" +
            // ", updatedBy='" + getUpdatedBy() + "'" +
            // ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
