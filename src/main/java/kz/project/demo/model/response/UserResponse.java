package kz.project.demo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private Long id;
    private String phone;
    private String name;
    private String city;
    private String qrImage;
    private int points;
    private String geolocation;
    private String description;
    private String image;
    private String point_image;
    private int experience;
    private String userLevel;
    private int bottomLine;
    private int upperLine;
    private int rawCalculation;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
