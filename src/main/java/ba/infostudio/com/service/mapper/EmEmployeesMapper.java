package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.EmEmployeesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmEmployees and its DTO EmEmployeesDTO.
 */
@Mapper(componentModel = "spring", uses = {RgQualificationsMapper.class, EmEmpTypesMapper.class, LeLegalEntitiesMapper.class, EmStatusesMapper.class})
public interface EmEmployeesMapper extends EntityMapper<EmEmployeesDTO, EmEmployees> {

    @Mapping(source = "idQualification.id", target = "idQualificationId")
    @Mapping(source = "idQualification.name", target = "idQualificationName")
    @Mapping(source = "idEmploymentType.id", target = "idEmploymentTypeId")
    @Mapping(source = "idEmploymentType.name", target = "idEmploymentTypeName")
    @Mapping(source = "idLegalEntity.id", target = "idLegalEntityId")
    @Mapping(source = "idLegalEntity.name", target = "idLegalEntityName")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    @Mapping(source = "idStatus.name", target = "idStatusName")
    // @Mapping(source = "idUser.id", target = "idUserId")
    EmEmployeesDTO toDto(EmEmployees emEmployees);

    @Mapping(source = "idQualificationId", target = "idQualification")
    @Mapping(source = "idEmploymentTypeId", target = "idEmploymentType")
    @Mapping(source = "idLegalEntityId", target = "idLegalEntity")
    @Mapping(source = "idStatusId", target = "idStatus")
    // @Mapping(source = "idUserId", target = "idUser")
    EmEmployees toEntity(EmEmployeesDTO emEmployeesDTO);

    default EmEmployees fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmEmployees emEmployees = new EmEmployees();
        emEmployees.setId(id);
        return emEmployees;
    }
}
