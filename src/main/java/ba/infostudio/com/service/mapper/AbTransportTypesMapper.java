package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbTransportTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbTransportTypes and its DTO AbTransportTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbTransportTypesMapper extends EntityMapper<AbTransportTypesDTO, AbTransportTypes> {



    default AbTransportTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbTransportTypes abTransportTypes = new AbTransportTypes();
        abTransportTypes.setId(id);
        return abTransportTypes;
    }
}
