package kz.project.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProcessorSchedule{
    private Long id;
    private int name;
    private String startAtTime;
    private String dayOfWeek;
    private String expiresAtTime;
    private Boolean isWeekend;
}
