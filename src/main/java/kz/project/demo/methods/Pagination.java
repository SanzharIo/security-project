package kz.project.demo.methods;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.response.UserResponse;
import lombok.AllArgsConstructor;
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

    public UserResponse convertToUserResponse(AuthorizedUser authorizedUser) {

        UserResponse userResponse = UserResponse.builder()
                .id(authorizedUser.getId())
                .phone(authorizedUser.getPhone())
                .name(authorizedUser.getName())
                .city(authorizedUser.getCity())
                .qrImage(authorizedUser.getQrImage())
                .points(authorizedUser.getPoints())
                .geolocation(authorizedUser.getGeolocation())
                .description(authorizedUser.getDescription())
                .image(authorizedUser.getImage())
                .point_image(authorizedUser.getPoint_image())
                .experience(authorizedUser.getExperience())
                .userLevel(authorizedUser.getUserLevel())
                .bottomLine(authorizedUser.getBottomLine())
                .upperLine(authorizedUser.getUpperLine())
                .rawCalculation(authorizedUser.getRawCalculation())
                .updatedAt(authorizedUser.getUpdatedAt())
                .deletedAt(authorizedUser.getDeletedAt())
                .createdAt(authorizedUser.getCreatedAt())
                .build();
        return userResponse;
    }
}
