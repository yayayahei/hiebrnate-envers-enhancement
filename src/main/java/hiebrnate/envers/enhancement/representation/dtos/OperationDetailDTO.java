package hiebrnate.envers.enhancement.representation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationDetailDTO {
    private String fieldName;
    private String newValue;
    private String oldValue;
}
