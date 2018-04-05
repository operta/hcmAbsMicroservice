package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbRequestStatusesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbRequestStatuses and its DTO AbRequestStatusesDTO.
 */
@Mapper(componentModel = "spring", uses = {AbStatusesMapper.class, AbRequestsMapper.class})
public interface AbRequestStatusesMapper extends EntityMapper<AbRequestStatusesDTO, AbRequestStatuses> {

    @Mapping(source = "idStatus.id", target = "idStatusId")
    @Mapping(source = "idStatus.name", target = "idStatusName")
    @Mapping(source = "idRequest.id", target = "idRequestId")
    AbRequestStatusesDTO toDto(AbRequestStatuses abRequestStatuses);

    @Mapping(source = "idStatusId", target = "idStatus")
    @Mapping(source = "idRequestId", target = "idRequest")
    AbRequestStatuses toEntity(AbRequestStatusesDTO abRequestStatusesDTO);

    default AbRequestStatuses fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbRequestStatuses abRequestStatuses = new AbRequestStatuses();
        abRequestStatuses.setId(id);
        return abRequestStatuses;
    }
}
