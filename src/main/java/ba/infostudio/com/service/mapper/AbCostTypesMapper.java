package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbCostTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbCostTypes and its DTO AbCostTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbCostTypesMapper extends EntityMapper<AbCostTypesDTO, AbCostTypes> {



    default AbCostTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbCostTypes abCostTypes = new AbCostTypes();
        abCostTypes.setId(id);
        return abCostTypes;
    }
}
