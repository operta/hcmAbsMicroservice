package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A AbVacationLeaveDays.
 */
@Entity
@Table(name = "ab_vacation_leave_days")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbVacationLeaveDays extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "number_of_days_last_y")
    private Integer numberOfDaysLastY;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

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

    public Integer getYear() {
        return year;
    }

    public AbVacationLeaveDays year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public AbVacationLeaveDays numberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfDaysLastY() {
        return numberOfDaysLastY;
    }

    public AbVacationLeaveDays numberOfDaysLastY(Integer numberOfDaysLastY) {
        this.numberOfDaysLastY = numberOfDaysLastY;
        return this;
    }

    public void setNumberOfDaysLastY(Integer numberOfDaysLastY) {
        this.numberOfDaysLastY = numberOfDaysLastY;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public AbVacationLeaveDays dateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public AbVacationLeaveDays dateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public EmEmployees getIdEmployee() {
        return idEmployee;
    }

    public AbVacationLeaveDays idEmployee(EmEmployees emEmployees) {
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
        AbVacationLeaveDays abVacationLeaveDays = (AbVacationLeaveDays) o;
        if (abVacationLeaveDays.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abVacationLeaveDays.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbVacationLeaveDays{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", numberOfDays=" + getNumberOfDays() +
            ", numberOfDaysLastY=" + getNumberOfDaysLastY() +
            ", dateFrom='" + getDateFrom() + "'" +
            ", dateTo='" + getDateTo() + "'" +
            "}";
    }
}
