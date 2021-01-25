package kz.project.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Processor {

    private String phone;
    private String name;
    private String city;
    private int city_id;
    private int points;
    private String geolocation;
    private String title;
    private String contact;
    private String description;
    private String image;
    private String point_image;
    private int experience;
    private String userLevel;
    private int bottomLine = 0;
    private int upperLine = 0;
    private int rawCalculation;
    private List<SubCategoryChildModel> subCategoryChildModels;
    private List<ProcessorSchedule> userSchedule;
    private List<CreateMap> collectionMap;

}
