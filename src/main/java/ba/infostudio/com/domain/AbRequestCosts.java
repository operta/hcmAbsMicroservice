package ba.infostudio.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A AbRequestCosts.
 */
@Entity
@Table(name = "ab_request_costs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AbRequestCosts extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "booked")
    private String booked;

    @OneToOne
    @JoinColumn(name = "id_request")
    private AbRequests idRequest;

    @OneToOne
    @JoinColumn(name = "id_cost_type")
    private AbCostTypes idCostType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public AbRequestCosts amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public AbRequestCosts currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBooked() {
        return booked;
    }

    public AbRequestCosts booked(String booked) {
        this.booked = booked;
        return this;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public AbRequests getIdRequest() {
        return idRequest;
    }

    public AbRequestCosts idRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
        return this;
    }

    public void setIdRequest(AbRequests abRequests) {
        this.idRequest = abRequests;
    }

    public AbCostTypes getIdCostType() {
        return idCostType;
    }

    public AbRequestCosts idCostType(AbCostTypes abCostTypes) {
        this.idCostType = abCostTypes;
        return this;
    }

    public void setIdCostType(AbCostTypes abCostTypes) {
        this.idCostType = abCostTypes;
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
        AbRequestCosts abRequestCosts = (AbRequestCosts) o;
        if (abRequestCosts.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestCosts.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestCosts{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currency='" + getCurrency() + "'" +
            ", booked='" + getBooked() + "'" +
            "}";
    }
}
