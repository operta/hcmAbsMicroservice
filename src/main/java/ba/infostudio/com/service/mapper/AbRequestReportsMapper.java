package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbRequestReportsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbRequestReports and its DTO AbRequestReportsDTO.
 */
@Mapper(componentModel = "spring", uses = {AbRequestsMapper.class, RgRegionsMapper.class, DmDocumentLinksMapper.class, DmDocumentTypesMapper.class})
public interface AbRequestReportsMapper extends EntityMapper<AbRequestReportsDTO, AbRequestReports> {

    @Mapping(source = "idRequest.id", target = "idRequestId")
    @Mapping(source = "idDepartureCountry.id", target = "idDepartureCountryId")
    @Mapping(source = "idDepartureCountry.name", target = "idDepartureCountryName")
    @Mapping(source = "idDeparturePlace.id", target = "idDeparturePlaceId")
    @Mapping(source = "idDeparturePlace.name", target = "idDeparturePlaceName")
    @Mapping(source = "idDestinationCountry.id", target = "idDestinationCountryId")
    @Mapping(source = "idDestinationCountry.name", target = "idDestinationCountryName")
    @Mapping(source = "idDestinationPlace.id", target = "idDestinationPlaceId")
    @Mapping(source = "idDestinationPlace.name", target = "idDestinationPlaceName")
    @Mapping(source = "idDocumentLink.id", target = "idDocumentLinkId")
    @Mapping(source = "idDocumentLink.documentBlob", target = "idDocumentLinkDocumentBlob")
    @Mapping(source = "idDocumentLink.documentBlobContentType", target = "idDocumentLinkDocumentBlobContentType")
    @Mapping(source = "idDocumentLink.filePath", target = "idDocumentLinkFilePath")
    @Mapping(source = "idDocumentType.id", target = "idDocumentTypeId")
    AbRequestReportsDTO toDto(AbRequestReports abRequestReports);

    @Mapping(source = "idRequestId", target = "idRequest")
    @Mapping(source = "idDepartureCountryId", target = "idDepartureCountry")
    @Mapping(source = "idDeparturePlaceId", target = "idDeparturePlace")
    @Mapping(source = "idDestinationCountryId", target = "idDestinationCountry")
    @Mapping(source = "idDestinationPlaceId", target = "idDestinationPlace")
    @Mapping(source = "idDocumentLinkId", target = "idDocumentLink")
    @Mapping(source = "idDocumentTypeId", target = "idDocumentType")
    AbRequestReports toEntity(AbRequestReportsDTO abRequestReportsDTO);

    default AbRequestReports fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbRequestReports abRequestReports = new AbRequestReports();
        abRequestReports.setId(id);
        return abRequestReports;
    }
}
