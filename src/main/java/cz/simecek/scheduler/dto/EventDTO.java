package cz.simecek.scheduler.dto;



import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class EventDTO {

    private Long id;

    private Timestamp createdAt;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
