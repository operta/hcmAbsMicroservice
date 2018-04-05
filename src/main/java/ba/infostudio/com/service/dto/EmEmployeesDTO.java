package ba.infostudio.com.service.dto;


import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EmEmployees entity.
 */
public class EmEmployeesDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    private String middleName;

    private String surname;

    private String maidenName;

    private Integer disabilityDegree;

    private String ethnicGroup;

    private String gender;

    private String residentialSituation;

    private String maritalStatus;

    private String bloodGroup;

    private LocalDate dateOfBirth;

    private LocalDate hireDate;

    private String ssn;

    private String taxNumber;

    private String phoneNumber;

    private String email;

    private String createdBy;

    private Instant createdAt;

    private String updatedBy;

    private Instant updatedAt;

    private Long idQualificationId;

    private String idQualificationName;

    private Long idEmploymentTypeId;

    private String idEmploymentTypeName;

    private Long idLegalEntityId;

    private String idLegalEntityName;

    private Long idStatusId;

    private String idStatusName;

    // private Long idUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public Integer getDisabilityDegree() {
        return disabilityDegree;
    }

    public void setDisabilityDegree(Integer disabilityDegree) {
        this.disabilityDegree = disabilityDegree;
    }

    public String getEthnicGroup() {
        return ethnicGroup;
    }

    public void setEthnicGroup(String ethnicGroup) {
        this.ethnicGroup = ethnicGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResidentialSituation() {
        return residentialSituation;
    }

    public void setResidentialSituation(String residentialSituation) {
        this.residentialSituation = residentialSituation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getIdQualificationId() {
        return idQualificationId;
    }

    public void setIdQualificationId(Long rgQualificationsId) {
        this.idQualificationId = rgQualificationsId;
    }

    public String getIdQualificationName() {
        return idQualificationName;
    }

    public void setIdQualificationName(String rgQualificationsName) {
        this.idQualificationName = rgQualificationsName;
    }

    public Long getIdEmploymentTypeId() {
        return idEmploymentTypeId;
    }

    public void setIdEmploymentTypeId(Long emEmpTypesId) {
        this.idEmploymentTypeId = emEmpTypesId;
    }

    public String getIdEmploymentTypeName() {
        return idEmploymentTypeName;
    }

    public void setIdEmploymentTypeName(String emEmpTypesName) {
        this.idEmploymentTypeName = emEmpTypesName;
    }

    public Long getIdLegalEntityId() {
        return idLegalEntityId;
    }

    public void setIdLegalEntityId(Long leLegalEntitiesId) {
        this.idLegalEntityId = leLegalEntitiesId;
    }

    public String getIdLegalEntityName() {
        return idLegalEntityName;
    }

    public void setIdLegalEntityName(String leLegalEntitiesName) {
        this.idLegalEntityName = leLegalEntitiesName;
    }

    public Long getIdStatusId() {
        return idStatusId;
    }

    public void setIdStatusId(Long emStatusesId) {
        this.idStatusId = emStatusesId;
    }

    public String getIdStatusName() {
        return idStatusName;
    }

    public void setIdStatusName(String emStatusesName) {
        this.idStatusName = emStatusesName;
    }

    // public Long getIdUserId() {
    //     return idUserId;
    // }

    // public void setIdUserId(Long userId) {
    //     this.idUserId = userId;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmEmployeesDTO emEmployeesDTO = (EmEmployeesDTO) o;
        if(emEmployeesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emEmployeesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmEmployeesDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", maidenName='" + getMaidenName() + "'" +
            ", disabilityDegree=" + getDisabilityDegree() +
            ", ethnicGroup='" + getEthnicGroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", residentialSituation='" + getResidentialSituation() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", ssn='" + getSsn() + "'" +
            ", taxNumber='" + getTaxNumber() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
