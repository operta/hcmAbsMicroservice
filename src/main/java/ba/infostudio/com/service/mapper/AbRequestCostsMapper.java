package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbRequestCostsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbRequestCosts and its DTO AbRequestCostsDTO.
 */
@Mapper(componentModel = "spring", uses = {AbRequestsMapper.class, AbCostTypesMapper.class})
public interface AbRequestCostsMapper extends EntityMapper<AbRequestCostsDTO, AbRequestCosts> {

    @Mapping(source = "idRequest.id", target = "idRequestId")
    @Mapping(source = "idCostType.id", target = "idCostTypeId")
    @Mapping(source = "idCostType.name", target = "idCostTypeName")
    AbRequestCostsDTO toDto(AbRequestCosts abRequestCosts);

    @Mapping(source = "idRequestId", target = "idRequest")
    @Mapping(source = "idCostTypeId", target = "idCostType")
    AbRequestCosts toEntity(AbRequestCostsDTO abRequestCostsDTO);

    default AbRequestCosts fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbRequestCosts abRequestCosts = new AbRequestCosts();
        abRequestCosts.setId(id);
        return abRequestCosts;
    }
}
