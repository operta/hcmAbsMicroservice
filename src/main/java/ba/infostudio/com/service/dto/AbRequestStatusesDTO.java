package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbRequestStatuses entity.
 */
public class AbRequestStatusesDTO implements Serializable {

    private Long id;

    private LocalDate dateFrom;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idStatusId;

    private String idStatusName;

    private Long idRequestId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(Long abStatusesId) {
        this.idStatusId = abStatusesId;
    }

    public String getIdStatusName() {
        return idStatusName;
    }

    public void setIdStatusName(String abStatusesName) {
        this.idStatusName = abStatusesName;
    }

    public Long getIdRequestId() {
        return idRequestId;
    }

    public void setIdRequestId(Long abRequestsId) {
        this.idRequestId = abRequestsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbRequestStatusesDTO abRequestStatusesDTO = (AbRequestStatusesDTO) o;
        if(abRequestStatusesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestStatusesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestStatusesDTO{" +
            "id=" + getId() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
