package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbRequests entity.
 */
public class AbRequestsDTO implements Serializable {

    private Long id;

    private String description;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Integer year;

    @NotNull
    private Integer noOfDays;

    @NotNull
    private Integer noOfDaysLeft;

    private Integer btAdvanceAmount;

    private String btAdvanceCurrency;

    private Long idAbsenceTypeId;

    private String idAbsenceTypeName;

    private Long idStatusId;

    private String idStatusName;

    private Long btIdAccomodationId;

    private String btIdAccomodationName;

    private Long btIdTransportId;

    private String btIdTransportName;

    private Long btIdPurposeId;

    private String btIdPurposeName;

    private Long btRegionCountryId;

    private String btRegionCountryName;

    private Long btRegionPlaceId;

    private String btRegionPlaceName;

    private Long idEmployeeId;

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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Integer getNoOfDaysLeft() {
        return noOfDaysLeft;
    }

    public void setNoOfDaysLeft(Integer noOfDaysLeft) {
        this.noOfDaysLeft = noOfDaysLeft;
    }

    public Integer getBtAdvanceAmount() {
        return btAdvanceAmount;
    }

    public void setBtAdvanceAmount(Integer btAdvanceAmount) {
        this.btAdvanceAmount = btAdvanceAmount;
    }

    public String getBtAdvanceCurrency() {
        return btAdvanceCurrency;
    }

    public void setBtAdvanceCurrency(String btAdvanceCurrency) {
        this.btAdvanceCurrency = btAdvanceCurrency;
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

    public Long getBtIdAccomodationId() {
        return btIdAccomodationId;
    }

    public void setBtIdAccomodationId(Long abAccomodationTypesId) {
        this.btIdAccomodationId = abAccomodationTypesId;
    }

    public String getBtIdAccomodationName() {
        return btIdAccomodationName;
    }

    public void setBtIdAccomodationName(String abAccomodationTypesName) {
        this.btIdAccomodationName = abAccomodationTypesName;
    }

    public Long getBtIdTransportId() {
        return btIdTransportId;
    }

    public void setBtIdTransportId(Long abTransportTypesId) {
        this.btIdTransportId = abTransportTypesId;
    }

    public String getBtIdTransportName() {
        return btIdTransportName;
    }

    public void setBtIdTransportName(String abTransportTypesName) {
        this.btIdTransportName = abTransportTypesName;
    }

    public Long getBtIdPurposeId() {
        return btIdPurposeId;
    }

    public void setBtIdPurposeId(Long abTravelPurposesId) {
        this.btIdPurposeId = abTravelPurposesId;
    }

    public String getBtIdPurposeName() {
        return btIdPurposeName;
    }

    public void setBtIdPurposeName(String abTravelPurposesName) {
        this.btIdPurposeName = abTravelPurposesName;
    }

    public Long getBtRegionCountryId() {
        return btRegionCountryId;
    }

    public void setBtRegionCountryId(Long rgRegionsId) {
        this.btRegionCountryId = rgRegionsId;
    }

    public String getBtRegionCountryName() {
        return btRegionCountryName;
    }

    public void setBtRegionCountryName(String rgRegionsName) {
        this.btRegionCountryName = rgRegionsName;
    }

    public Long getBtRegionPlaceId() {
        return btRegionPlaceId;
    }

    public void setBtRegionPlaceId(Long rgRegionsId) {
        this.btRegionPlaceId = rgRegionsId;
    }

    public String getBtRegionPlaceName() {
        return btRegionPlaceName;
    }

    public void setBtRegionPlaceName(String rgRegionsName) {
        this.btRegionPlaceName = rgRegionsName;
    }

    public Long getIdEmployeeId() {
        return idEmployeeId;
    }

    public void setIdEmployeeId(Long emEmployeesId) {
        this.idEmployeeId = emEmployeesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbRequestsDTO abRequestsDTO = (AbRequestsDTO) o;
        if(abRequestsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestsDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", year=" + getYear() +
            ", noOfDays=" + getNoOfDays() +
            ", noOfDaysLeft=" + getNoOfDaysLeft() +
            ", btAdvanceAmount=" + getBtAdvanceAmount() +
            ", btAdvanceCurrency='" + getBtAdvanceCurrency() + "'" +
            "}";
    }
}
