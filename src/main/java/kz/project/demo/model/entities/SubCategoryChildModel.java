package kz.project.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubCategoryChildModel {
    private Long id;
    private int name;
    private String image;
    private String description;
    private String innerImage;
    private String color;
    private int weight;



}
