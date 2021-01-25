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
public class ProcessorSchedule {

    @Id
    private Long id;
    private int name;
    private String startAtTime;
    private String dayOfWeek;
    private String expiresAtTime;
    private Boolean isWeekend;
}
