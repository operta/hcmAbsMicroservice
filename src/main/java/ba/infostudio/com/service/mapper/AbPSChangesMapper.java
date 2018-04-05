package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbPSChangesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbPSChanges and its DTO AbPSChangesDTO.
 */
@Mapper(componentModel = "spring", uses = {AbAbsenceTypesMapper.class, AbStatusesMapper.class})
public interface AbPSChangesMapper extends EntityMapper<AbPSChangesDTO, AbPSChanges> {

    @Mapping(source = "idAbsenceType.id", target = "idAbsenceTypeId")
    @Mapping(source = "idAbsenceType.name", target = "idAbsenceTypeName")
    @Mapping(source = "idStatusFrom.id", target = "idStatusFromId")
    @Mapping(source = "idStatusFrom.name", target = "idStatusFromName")
    @Mapping(source = "idStatusTo.id", target = "idStatusToId")
    @Mapping(source = "idStatusTo.name", target = "idStatusToName")
    AbPSChangesDTO toDto(AbPSChanges abPSChanges);

    @Mapping(source = "idAbsenceTypeId", target = "idAbsenceType")
    @Mapping(source = "idStatusFromId", target = "idStatusFrom")
    @Mapping(source = "idStatusToId", target = "idStatusTo")
    AbPSChanges toEntity(AbPSChangesDTO abPSChangesDTO);

    default AbPSChanges fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbPSChanges abPSChanges = new AbPSChanges();
        abPSChanges.setId(id);
        return abPSChanges;
    }
}
