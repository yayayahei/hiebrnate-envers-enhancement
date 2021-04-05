package hiebrnate.envers.enhancement.representation.dtos.mapper;


import hiebrnate.envers.enhancement.domain.entities.OperationLog;
import hiebrnate.envers.enhancement.domain.entities.OperationLogDetail;
import hiebrnate.envers.enhancement.representation.dtos.OperationDetailDTO;
import hiebrnate.envers.enhancement.representation.dtos.OperationLogDTO;
import hiebrnate.envers.enhancement.representation.dtos.response.CommonPageResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationLogMapper {
    OperationLogMapper INSTANCE = Mappers.getMapper(OperationLogMapper.class);

    @Mapping(target = "operationDetails",source = "operationLogDetails")
    OperationLogDTO toOperationLogDTO(OperationLog operationLog);
//
//    @AfterMapping
//    default void fillOperationDetail(final OperationLog operationLog,
//                                     final @MappingTarget OperationLogDTO operationLogDTO) {
//        operationLogDTO.setOperationDetails(toOperationDetailDTOList(operationLog.getOperationLogDetails()));
//    }

    List<OperationLogDTO> toOperationLogDTOList(List<OperationLog> operationLogs);

    CommonPageResponse<OperationLogDTO> toCommonPageResponse(CommonPageResponse<OperationLog> commonPageResponse);

    OperationDetailDTO toOperationDetailDTO(OperationLogDetail operationLogDetail);

    List<OperationDetailDTO> toOperationDetailDTOList(List<OperationLogDetail> operationLogDetails);
}
