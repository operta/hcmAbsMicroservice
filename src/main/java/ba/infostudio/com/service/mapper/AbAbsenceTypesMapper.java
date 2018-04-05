package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbAbsenceTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbAbsenceTypes and its DTO AbAbsenceTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbAbsenceTypesMapper extends EntityMapper<AbAbsenceTypesDTO, AbAbsenceTypes> {



    default AbAbsenceTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbAbsenceTypes abAbsenceTypes = new AbAbsenceTypes();
        abAbsenceTypes.setId(id);
        return abAbsenceTypes;
    }
}
