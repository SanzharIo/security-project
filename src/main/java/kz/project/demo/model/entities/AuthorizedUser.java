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

    @Column(name = "phone",unique = true)
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
    private int bottomLine = 0;

    @Column(name = "upper_line")
    private int upperLine = 0   ;

    @Column(name = "raw_calculation")
    private int rawCalculation;

    @Column(name = "validation_key")
    private String validationKey;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<SubCategoryChildModel> subCategoryChildModels;
//
//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<ProcessorSchedule> userSchedule;
//
//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    private List<CreateMap> collectionMap;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;
}
