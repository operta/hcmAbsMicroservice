package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AbRequestCosts entity.
 */
public class AbRequestCostsDTO implements Serializable {

    private Long id;

    private Integer amount;

    private String currency;

    private String booked;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idRequestId;

    private Long idCostTypeId;

    private String idCostTypeName;

    private Double amountDollar;

    public Double getAmountDollar() {
        return amountDollar;
    }

    public void setAmountDollar(Double amountDollar) {
        this.amountDollar = amountDollar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
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

    public Long getIdCostTypeId() {
        return idCostTypeId;
    }

    public void setIdCostTypeId(Long abCostTypesId) {
        this.idCostTypeId = abCostTypesId;
    }

    public String getIdCostTypeName() {
        return idCostTypeName;
    }

    public void setIdCostTypeName(String abCostTypesName) {
        this.idCostTypeName = abCostTypesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbRequestCostsDTO abRequestCostsDTO = (AbRequestCostsDTO) o;
        if(abRequestCostsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abRequestCostsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbRequestCostsDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currency='" + getCurrency() + "'" +
            ", booked='" + getBooked() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
