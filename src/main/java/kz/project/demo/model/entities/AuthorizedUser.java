package kz.project.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.project.demo.model.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizedUser extends AuditModel implements Serializable {

    @Column(name = "phone")
    @JsonProperty("username")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "city_id")
    private int city_id;

    @Column(name = "geolocation")
    private String geolocation;

    @Column(name = "title")
    private String title;

    @Column(name = "contact")
    private String contact;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "point_image")
    private String point_image;

    @Column(name = "experience")
    private int experience;

    @Column(name = "user_level")
    private String userLevel;

    @Column(name = "bottom_line")
    private int bottomLine;

    @Column(name = "upper_line")
    private int upperLine;

    @Column(name = "raw_calculation")
    private int rawCalculation;

    @Column(name = "subcategory")
    private List<SubCategoryChildModel> subCategoryChildModels;

    @Column(name = "user_schedule")
    private List<ProcessorSchedule> userSchedule;

    @Column(name = "collection_map")
    private List<CreateMap> collectionMap;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;
}
