package pl.akademiaqa.dto.task.request;

import lombok.Data;

@Data
public class CreateTaskRequestDto {
    private String name;
    private String description;
    private String status;
    private String priority;
    private String parent;
    private String timeEstimate;
    private String assignees;
    private String archived;
}
