package hiebrnate.envers.enhancement.representation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogDTO {
    private String operationUser;
    private String operationType;
    private Date operationTime;
    private List<OperationDetailDTO> operationDetails;
}
