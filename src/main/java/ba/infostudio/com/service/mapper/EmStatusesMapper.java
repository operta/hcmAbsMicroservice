package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.EmStatusesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EmStatuses and its DTO EmStatusesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmStatusesMapper extends EntityMapper<EmStatusesDTO, EmStatuses> {



    default EmStatuses fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmStatuses emStatuses = new EmStatuses();
        emStatuses.setId(id);
        return emStatuses;
    }
}
