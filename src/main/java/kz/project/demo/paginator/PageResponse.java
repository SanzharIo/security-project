package kz.project.demo.paginator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    private List<?> content;
    private Integer totalPages;
    private Long totalElements;
    private Boolean last;
    private Integer size;
    private Integer number;
    private Integer numberOfElements;
    private Boolean first;
    private SortResponse sortResponse;
    private Boolean empty;
    private PageableResponse pageable;
    private Double sum = 0.0;
    private Double bonus = 0.0;
    private Double bonusGained = 0.0;
    private Double cash = 0.0;
    private Double creditCard = 0.0;
}
