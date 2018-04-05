package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AbRequests.
 */
@Entity
@Table(name = "ab_requests")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbRequests extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "year")
    private Integer year;

    @NotNull
    @Column(name = "no_of_days", nullable = false)
    private Integer noOfDays;

    @NotNull
    @Column(name = "no_of_days_left", nullable = false)
    private Integer noOfDaysLeft;

    @Column(name = "bt_advance_amount")
    private Integer btAdvanceAmount;

    @Column(name = "bt_advance_currency")
    private String btAdvanceCurrency;

    @OneToOne
    @JoinColumn(name = "id_absence_type")
    private AbAbsenceTypes idAbsenceType;

    @OneToOne
    @JoinColumn(name = "id_status")
    private AbStatuses idStatus;

    @OneToOne
    @JoinColumn(name = "bt_id_accomodation")
    private AbAccomodationTypes btIdAccomodation;

    @OneToOne
    @JoinColumn(name = "bt_id_transport")
    private AbTransportTypes btIdTransport;

    @OneToOne
    @JoinColumn(name = "bt_id_purpose")
    private AbTravelPurposes btIdPurpose;

    @OneToOne
    @JoinColumn(name = "bt_region_country")
    private RgRegions btRegionCountry;

    @OneToOne
    @JoinColumn(name = "bt_region_place")
    private RgRegions btRegionPlace;

    @OneToOne
    @JoinColumn(name = "id_employee")
    private EmEmployees idEmployee;

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

    public AbRequests description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public AbRequests dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public AbRequests dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getYear() {
        return year;
    }

    public AbRequests year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public AbRequests noOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
        return this;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Integer getNoOfDaysLeft() {
        return noOfDaysLeft;
    }

    public AbRequests noOfDaysLeft(Integer noOfDaysLeft) {
        this.noOfDaysLeft = noOfDaysLeft;
        return this;
    }

    public void setNoOfDaysLeft(Integer noOfDaysLeft) {
        this.noOfDaysLeft = noOfDaysLeft;
    }

    public Integer getBtAdvanceAmount() {
        return btAdvanceAmount;
    }

    public AbRequests btAdvanceAmount(Integer btAdvanceAmount) {
        this.btAdvanceAmount = btAdvanceAmount;
        return this;
    }

    public void setBtAdvanceAmount(Integer btAdvanceAmount) {
        this.btAdvanceAmount = btAdvanceAmount;
    }

    public String getBtAdvanceCurrency() {
        return btAdvanceCurrency;
    }

    public AbRequests btAdvanceCurrency(String btAdvanceCurrency) {
        this.btAdvanceCurrency = btAdvanceCurrency;
        return this;
    }

    public void setBtAdvanceCurrency(String btAdvanceCurrency) {
        this.btAdvanceCurrency = btAdvanceCurrency;
    }

    public AbAbsenceTypes getIdAbsenceType() {
        return idAbsenceType;
    }

    public AbRequests idAbsenceType(AbAbsenceTypes abAbsenceTypes) {
        this.idAbsenceType = abAbsenceTypes;
        return this;
    }

    public void setIdAbsenceType(AbAbsenceTypes abAbsenceTypes) {
        this.idAbsenceType = abAbsenceTypes;
    }

    public AbStatuses getIdStatus() {
        return idStatus;
    }

    public AbRequests idStatus(AbStatuses abStatuses) {
        this.idStatus = abStatuses;
        return this;
    }

    public void setIdStatus(AbStatuses abStatuses) {
        this.idStatus = abStatuses;
    }

    public AbAccomodationTypes getBtIdAccomodation() {
        return btIdAccomodation;
    }

    public AbRequests btIdAccomodation(AbAccomodationTypes abAccomodationTypes) {
        this.btIdAccomodation = abAccomodationTypes;
        return this;
    }

    public void setBtIdAccomodation(AbAccomodationTypes abAccomodationTypes) {
        this.btIdAccomodation = abAccomodationTypes;
    }

    public AbTransportTypes getBtIdTransport() {
        return btIdTransport;
    }

    public AbRequests btIdTransport(AbTransportTypes abTransportTypes) {
        this.btIdTransport = abTransportTypes;
        return this;
    }

    public void setBtIdTransport(AbTransportTypes abTransportTypes) {
        this.btIdTransport = abTransportTypes;
    }

    public AbTravelPurposes getBtIdPurpose() {
        return btIdPurpose;
    }

    public AbRequests btIdPurpose(AbTravelPurposes abTravelPurposes) {
        this.btIdPurpose = abTravelPurposes;
        return this;
    }

    public void setBtIdPurpose(AbTravelPurposes abTravelPurposes) {
        this.btIdPurpose = abTravelPurposes;
    }

    public RgRegions getBtRegionCountry() {
        return btRegionCountry;
    }

    public AbRequests btRegionCountry(RgRegions rgRegions) {
        this.btRegionCountry = rgRegions;
        return this;
    }

    public void setBtRegionCountry(RgRegions rgRegions) {
        this.btRegionCountry = rgRegions;
    }

    public RgRegions getBtRegionPlace() {
        return btRegionPlace;
    }

    public AbRequests btRegionPlace(RgRegions rgRegions) {
        this.btRegionPlace = rgRegions;
        return this;
    }

    public void setBtRegionPlace(RgRegions rgRegions) {
        this.btRegionPlace = rgRegions;
    }

    public EmEmployees getIdEmployee() {
        return idEmployee;
    }

    public AbRequests idEmployee(EmEmployees emEmployees) {
        this.idEmployee = emEmployees;
        return this;
    }

    public void setIdEmployee(EmEmployees emEmployees) {
        this.idEmployee = emEmployees;
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
        AbRequests abRequests = (AbRequests) o;
        if (abRequests.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequests.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequests{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
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
