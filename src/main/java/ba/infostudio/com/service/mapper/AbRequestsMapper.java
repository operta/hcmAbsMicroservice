package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbRequestsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbRequests and its DTO AbRequestsDTO.
 */
@Mapper(componentModel = "spring", uses = {AbAbsenceTypesMapper.class, AbStatusesMapper.class, AbAccomodationTypesMapper.class, AbTransportTypesMapper.class, AbTravelPurposesMapper.class, RgRegionsMapper.class, EmEmployeesMapper.class})
public interface AbRequestsMapper extends EntityMapper<AbRequestsDTO, AbRequests> {

    @Mapping(source = "idAbsenceType.id", target = "idAbsenceTypeId")
    @Mapping(source = "idAbsenceType.name", target = "idAbsenceTypeName")
    @Mapping(source = "idStatus.id", target = "idStatusId")
    @Mapping(source = "idStatus.name", target = "idStatusName")
    @Mapping(source = "btIdAccomodation.id", target = "btIdAccomodationId")
    @Mapping(source = "btIdAccomodation.name", target = "btIdAccomodationName")
    @Mapping(source = "btIdTransport.id", target = "btIdTransportId")
    @Mapping(source = "btIdTransport.name", target = "btIdTransportName")
    @Mapping(source = "btIdPurpose.id", target = "btIdPurposeId")
    @Mapping(source = "btIdPurpose.name", target = "btIdPurposeName")
    @Mapping(source = "btRegionCountry.id", target = "btRegionCountryId")
    @Mapping(source = "btRegionCountry.name", target = "btRegionCountryName")
    @Mapping(source = "btRegionPlace.id", target = "btRegionPlaceId")
    @Mapping(source = "btRegionPlace.name", target = "btRegionPlaceName")
    @Mapping(source = "idEmployee.id", target = "idEmployeeId")
    @Mapping(source = "idEmployee.name", target = "idEmployeeName")
    @Mapping(source = "idEmployee.surname", target = "idEmployeeSurname")
    AbRequestsDTO toDto(AbRequests abRequests);

    @Mapping(source = "idAbsenceTypeId", target = "idAbsenceType")
    @Mapping(source = "idStatusId", target = "idStatus")
    @Mapping(source = "btIdAccomodationId", target = "btIdAccomodation")
    @Mapping(source = "btIdTransportId", target = "btIdTransport")
    @Mapping(source = "btIdPurposeId", target = "btIdPurpose")
    @Mapping(source = "btRegionCountryId", target = "btRegionCountry")
    @Mapping(source = "btRegionPlaceId", target = "btRegionPlace")
    @Mapping(source = "idEmployeeId", target = "idEmployee")
    AbRequests toEntity(AbRequestsDTO abRequestsDTO);

    default AbRequests fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbRequests abRequests = new AbRequests();
        abRequests.setId(id);
        return abRequests;
    }
}
