package kz.project.demo.methods;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.paginator.PageResponse;
import kz.project.demo.paginator.PageableResponse;
import kz.project.demo.paginator.SortResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Pagination {

    public PageRequest paginate(Optional<Integer> page,
                                Optional<Integer> size,
                                Optional<String[]> sortBy) {
        Sort sort = Sort.by("id");
        if (sortBy.isPresent()) {
            String[] sorters = sortBy.get();
            List<Sort.Order> sorts = Arrays.stream(sorters)
                    .map(s -> s.split("-")[0].trim().equalsIgnoreCase("asc")
                            ? Sort.Order.asc(s.split("-")[1]) : Sort.Order.desc(s.split("-")[1]))
                    .collect(Collectors.toList());
            sort = Sort.by(sorts);
        }
        return PageRequest.of(page.orElse(0), size.orElse(5), sort);
    }

    public PageResponse convertToPageResponse(List<AuthorizedUser> users, Page<?> page) {
        SortResponse sortResponse = SortResponse.builder()
                .sorted(page.getSort().isSorted())
                .unsorted(page.getSort().isUnsorted())
                .empty(page.getSort().isEmpty())
                .build();

        PageableResponse pageableResponse = PageableResponse.builder()
                .sortResponse(sortResponse)
                .pageNumber(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .paged(page.getPageable().isPaged())
                .build();

        return PageResponse.builder()
                .totalPages(page.getTotalPages())
                .content(users)
                .last(page.isLast())
                .first(page.isFirst())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .pageable(pageableResponse)
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}
