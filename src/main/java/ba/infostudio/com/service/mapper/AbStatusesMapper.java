package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbStatusesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbStatuses and its DTO AbStatusesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AbStatusesMapper extends EntityMapper<AbStatusesDTO, AbStatuses> {



    default AbStatuses fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbStatuses abStatuses = new AbStatuses();
        abStatuses.setId(id);
        return abStatuses;
    }
}
