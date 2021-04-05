package hiebrnate.envers.enhancement.domain.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OperationLogDetail {
    private String fieldName;
    private String oldValue;
    private String newValue;
}
