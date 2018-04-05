package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbAccomodationTypesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbAccomodationTypes and its DTO AbAccomodationTypesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbAccomodationTypesMapper extends EntityMapper<AbAccomodationTypesDTO, AbAccomodationTypes> {



    default AbAccomodationTypes fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbAccomodationTypes abAccomodationTypes = new AbAccomodationTypes();
        abAccomodationTypes.setId(id);
        return abAccomodationTypes;
    }
}
