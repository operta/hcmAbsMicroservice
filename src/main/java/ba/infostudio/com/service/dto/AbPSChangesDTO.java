package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbPSChanges entity.
 */
public class AbPSChangesDTO implements Serializable {

    private Long id;

    private String description;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idAbsenceTypeId;

    private String idAbsenceTypeName;

    private Long idStatusFromId;

    private String idStatusFromName;

    private Long idStatusToId;

    private String idStatusToName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getIdAbsenceTypeId() {
        return idAbsenceTypeId;
    }

    public void setIdAbsenceTypeId(Long abAbsenceTypesId) {
        this.idAbsenceTypeId = abAbsenceTypesId;
    }

    public String getIdAbsenceTypeName() {
        return idAbsenceTypeName;
    }

    public void setIdAbsenceTypeName(String abAbsenceTypesName) {
        this.idAbsenceTypeName = abAbsenceTypesName;
    }

    public Long getIdStatusFromId() {
        return idStatusFromId;
    }

    public void setIdStatusFromId(Long abStatusesId) {
        this.idStatusFromId = abStatusesId;
    }

    public String getIdStatusFromName() {
        return idStatusFromName;
    }

    public void setIdStatusFromName(String abStatusesName) {
        this.idStatusFromName = abStatusesName;
    }

    public Long getIdStatusToId() {
        return idStatusToId;
    }

    public void setIdStatusToId(Long abStatusesId) {
        this.idStatusToId = abStatusesId;
    }

    public String getIdStatusToName() {
        return idStatusToName;
    }

    public void setIdStatusToName(String abStatusesName) {
        this.idStatusToName = abStatusesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbPSChangesDTO abPSChangesDTO = (AbPSChangesDTO) o;
        if(abPSChangesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abPSChangesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbPSChangesDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
