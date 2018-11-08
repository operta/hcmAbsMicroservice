package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A AbRequestReports.
 */
@Entity
@Table(name = "ab_request_reports")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbRequestReports implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_request")
    private AbRequests idRequest;

    @OneToOne
    @JoinColumn(name = "id_departure_country")
    private RgRegions idDepartureCountry;

    @OneToOne
    @JoinColumn(name = "id_departure_place")
    private RgRegions idDeparturePlace;

    @OneToOne
    @JoinColumn(name = "id_destination_country")
    private RgRegions idDestinationCountry;

    @OneToOne
    @JoinColumn(name = "id_destination_place")
    private RgRegions idDestinationPlace;

    @OneToOne
    @JoinColumn(name = "id_document_link")
    private DmDocumentLinks idDocumentLink;

    @OneToOne
    @JoinColumn(name = "id_document_type")
    private DmDocumentTypes idDocumentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public AbRequestReports departureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public AbRequestReports arrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDescription() {
        return description;
    }

    public AbRequestReports description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbRequests getIdRequest() {
        return idRequest;
    }

    public AbRequestReports idRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
        return this;
    }

    public void setIdRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
    }

    public RgRegions getIdDepartureCountry() {
        return idDepartureCountry;
    }

    public AbRequestReports idDepartureCountry(RgRegions rgRegions) {
        this.idDepartureCountry = rgRegions;
        return this;
    }

    public void setIdDepartureCountry(RgRegions rgRegions) {
        this.idDepartureCountry = rgRegions;
    }

    public RgRegions getIdDeparturePlace() {
        return idDeparturePlace;
    }

    public AbRequestReports idDeparturePlace(RgRegions rgRegions) {
        this.idDeparturePlace = rgRegions;
        return this;
    }

    public void setIdDeparturePlace(RgRegions rgRegions) {
        this.idDeparturePlace = rgRegions;
    }

    public RgRegions getIdDestinationCountry() {
        return idDestinationCountry;
    }

    public AbRequestReports idDestinationCountry(RgRegions rgRegions) {
        this.idDestinationCountry = rgRegions;
        return this;
    }

    public void setIdDestinationCountry(RgRegions rgRegions) {
        this.idDestinationCountry = rgRegions;
    }

    public RgRegions getIdDestinationPlace() {
        return idDestinationPlace;
    }

    public AbRequestReports idDestinationPlace(RgRegions rgRegions) {
        this.idDestinationPlace = rgRegions;
        return this;
    }

    public void setIdDestinationPlace(RgRegions rgRegions) {
        this.idDestinationPlace = rgRegions;
    }

    public DmDocumentLinks getIdDocumentLink() {
        return idDocumentLink;
    }

    public AbRequestReports idDocumentLink(DmDocumentLinks dmDocumentLinks) {
        this.idDocumentLink = dmDocumentLinks;
        return this;
    }

    public void setIdDocumentLink(DmDocumentLinks dmDocumentLinks) {
        this.idDocumentLink = dmDocumentLinks;
    }

    public DmDocumentTypes getIdDocumentType() {
        return idDocumentType;
    }

    public AbRequestReports idDocumentType(DmDocumentTypes dmDocumentTypes) {
        this.idDocumentType = dmDocumentTypes;
        return this;
    }

    public void setIdDocumentType(DmDocumentTypes dmDocumentTypes) {
        this.idDocumentType = dmDocumentTypes;
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
        AbRequestReports abRequestReports = (AbRequestReports) o;
        if (abRequestReports.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestReports.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestReports{" +
            "id=" + getId() +
            ", departureTime='" + getDepartureTime() + "'" +
            ", arrivalTime='" + getArrivalTime() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
