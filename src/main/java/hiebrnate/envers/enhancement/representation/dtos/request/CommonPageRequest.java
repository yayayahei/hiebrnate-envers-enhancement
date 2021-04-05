package hiebrnate.envers.enhancement.representation.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageRequest {

    private static final int MAX_PAGE_SIZE = 50;

    private static final String PAGE_NUMBER_ERROR_MESSAGE = "should be grater than or equal to 0";

    private static final String PAGE_SIZE_ERROR_MESSAGE = "should between 1 and 50";

    @Min(value = 0, message = PAGE_NUMBER_ERROR_MESSAGE)
    @NotNull
    private Integer pageNumber;

    @Min(value = 1, message = PAGE_SIZE_ERROR_MESSAGE)
    @NotNull
    @Max(value = MAX_PAGE_SIZE, message = PAGE_SIZE_ERROR_MESSAGE)
    private Integer pageSize;
}
