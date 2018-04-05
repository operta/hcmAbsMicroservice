package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbTravelPurposesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbTravelPurposes and its DTO AbTravelPurposesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbTravelPurposesMapper extends EntityMapper<AbTravelPurposesDTO, AbTravelPurposes> {



    default AbTravelPurposes fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbTravelPurposes abTravelPurposes = new AbTravelPurposes();
        abTravelPurposes.setId(id);
        return abTravelPurposes;
    }
}
