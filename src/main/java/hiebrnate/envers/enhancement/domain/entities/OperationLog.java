package hiebrnate.envers.enhancement.domain.entities;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class OperationLog {
    private String dataTable;
    private Long dataId;
    private String operationUser;
    private String operationType;
    private String operationMode;
    private Date operationTime;
    private List<OperationLogDetail> operationLogDetails;
}
