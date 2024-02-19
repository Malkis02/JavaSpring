package hw.HomeWork4.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Tasks")
public class Task {
    public enum TaskStatus {
        NOT_STARTED, IN_PROGRESS, COMPLETED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private LocalDateTime dateTaskCreate;

    @PrePersist
    protected void onCreate() {
        dateTaskCreate = LocalDateTime.now();
    }
}
