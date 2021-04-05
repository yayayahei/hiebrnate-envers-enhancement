package hiebrnate.envers.enhancement.representation.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageResponse<T> {

    private int pageNumber;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private List<T> content;

    public static <T> CommonPageResponse<T> of(Page page) {
        return CommonPageResponse.<T>builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
