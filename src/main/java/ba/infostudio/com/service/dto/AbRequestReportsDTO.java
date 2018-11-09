package ba.infostudio.com.service.dto;

import javax.persistence.Lob;
import java.time.Instant;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the AbRequestReports entity.
 */
public class AbRequestReportsDTO implements Serializable {

    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String description;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idRequestId;

    private Long idDepartureCountryId;

    private String idDepartureCountryName;

    private Long idDeparturePlaceId;

    private String idDeparturePlaceName;

    private Long idDestinationCountryId;

    private String idDestinationCountryName;

    private Long idDestinationPlaceId;

    private String idDestinationPlaceName;

    private Long idDocumentLinkId;

    private Long idDocumentTypeId;

    @Lob
    private byte[] idDocumentLinkDocumentBlob;

    private String idDocumentLinkDocumentBlobContentType;

    public byte[] getIdDocumentLinkDocumentBlob() {
        return idDocumentLinkDocumentBlob;
    }

    public void setIdDocumentLinkDocumentBlob(byte[] idDocumentLinkDocumentBlob) {
        this.idDocumentLinkDocumentBlob = idDocumentLinkDocumentBlob;
    }

    public String getIdDocumentLinkDocumentBlobContentType() {
        return idDocumentLinkDocumentBlobContentType;
    }

    public void setIdDocumentLinkDocumentBlobContentType(String idDocumentLinkDocumentBlobContentType) {
        this.idDocumentLinkDocumentBlobContentType = idDocumentLinkDocumentBlobContentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public Long getIdRequestId() {
        return idRequestId;
    }

    public void setIdRequestId(Long abRequestsId) {
        this.idRequestId = abRequestsId;
    }

    public Long getIdDepartureCountryId() {
        return idDepartureCountryId;
    }

    public void setIdDepartureCountryId(Long rgRegionsId) {
        this.idDepartureCountryId = rgRegionsId;
    }

    public String getIdDepartureCountryName() {
        return idDepartureCountryName;
    }

    public void setIdDepartureCountryName(String rgRegionsName) {
        this.idDepartureCountryName = rgRegionsName;
    }

    public Long getIdDeparturePlaceId() {
        return idDeparturePlaceId;
    }

    public void setIdDeparturePlaceId(Long rgRegionsId) {
        this.idDeparturePlaceId = rgRegionsId;
    }

    public String getIdDeparturePlaceName() {
        return idDeparturePlaceName;
    }

    public void setIdDeparturePlaceName(String rgRegionsName) {
        this.idDeparturePlaceName = rgRegionsName;
    }

    public Long getIdDestinationCountryId() {
        return idDestinationCountryId;
    }

    public void setIdDestinationCountryId(Long rgRegionsId) {
        this.idDestinationCountryId = rgRegionsId;
    }

    public String getIdDestinationCountryName() {
        return idDestinationCountryName;
    }

    public void setIdDestinationCountryName(String rgRegionsName) {
        this.idDestinationCountryName = rgRegionsName;
    }

    public Long getIdDestinationPlaceId() {
        return idDestinationPlaceId;
    }

    public void setIdDestinationPlaceId(Long rgRegionsId) {
        this.idDestinationPlaceId = rgRegionsId;
    }

    public String getIdDestinationPlaceName() {
        return idDestinationPlaceName;
    }

    public void setIdDestinationPlaceName(String rgRegionsName) {
        this.idDestinationPlaceName = rgRegionsName;
    }

    public Long getIdDocumentLinkId() {
        return idDocumentLinkId;
    }

    public void setIdDocumentLinkId(Long dmDocumentLinksId) {
        this.idDocumentLinkId = dmDocumentLinksId;
    }

    public Long getIdDocumentTypeId() {
        return idDocumentTypeId;
    }

    public void setIdDocumentTypeId(Long dmDocumentTypesId) {
        this.idDocumentTypeId = dmDocumentTypesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbRequestReportsDTO abRequestReportsDTO = (AbRequestReportsDTO) o;
        if(abRequestReportsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestReportsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestReportsDTO{" +
            "id=" + getId() +
            ", departureTime='" + getDepartureTime() + "'" +
            ", arrivalTime='" + getArrivalTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
