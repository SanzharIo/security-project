package kz.project.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubCategoryChildModel {

    @Id
    private Long id;
    private int name;
    private String image;
    private String description;
    private String innerImage;
    private String color;
    private int weight;


}
