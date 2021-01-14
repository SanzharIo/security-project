package kz.project.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    private String phone;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String rePassword;

}
