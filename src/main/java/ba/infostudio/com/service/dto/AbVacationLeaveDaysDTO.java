package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbVacationLeaveDays entity.
 */
public class AbVacationLeaveDaysDTO implements Serializable {

    private Long id;

    private Integer year;

    private Integer numberOfDays;

    private Integer numberOfDaysLastY;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idEmployeeId;

    private String idEmployeeName;

    private String idEmployeeSurname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfDaysLastY() {
        return numberOfDaysLastY;
    }

    public void setNumberOfDaysLastY(Integer numberOfDaysLastY) {
        this.numberOfDaysLastY = numberOfDaysLastY;
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

    public Long getIdEmployeeId() {
        return idEmployeeId;
    }

    public void setIdEmployeeId(Long emEmployeesId) {
        this.idEmployeeId = emEmployeesId;
    }

    public String getIdEmployeeName() {
        return idEmployeeName;
    }

    public void setIdEmployeeName(String idEmployeeName) {
        this.idEmployeeName = idEmployeeName;
    }

    public String getIdEmployeeSurname() {
        return idEmployeeSurname;
    }

    public void setIdEmployeeSurname(String idEmployeeSurname) {
        this.idEmployeeSurname = idEmployeeSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbVacationLeaveDaysDTO abVacationLeaveDaysDTO = (AbVacationLeaveDaysDTO) o;
        if(abVacationLeaveDaysDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abVacationLeaveDaysDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbVacationLeaveDaysDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", numberOfDays=" + getNumberOfDays() +
            ", numberOfDaysLastY=" + getNumberOfDaysLastY() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
