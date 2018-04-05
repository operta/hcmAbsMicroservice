package ba.infostudio.com.service.mapper;

import ba.infostudio.com.domain.*;
import ba.infostudio.com.service.dto.AbVacationLeaveDaysDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbVacationLeaveDays and its DTO AbVacationLeaveDaysDTO.
 */
@Mapper(componentModel = "spring", uses = {EmEmployeesMapper.class})
public interface AbVacationLeaveDaysMapper extends EntityMapper<AbVacationLeaveDaysDTO, AbVacationLeaveDays> {

    @Mapping(source = "idEmployee.id", target = "idEmployeeId")
    AbVacationLeaveDaysDTO toDto(AbVacationLeaveDays abVacationLeaveDays);

    @Mapping(source = "idEmployeeId", target = "idEmployee")
    AbVacationLeaveDays toEntity(AbVacationLeaveDaysDTO abVacationLeaveDaysDTO);

    default AbVacationLeaveDays fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbVacationLeaveDays abVacationLeaveDays = new AbVacationLeaveDays();
        abVacationLeaveDays.setId(id);
        return abVacationLeaveDays;
    }
}
