package ru.ageev.tasklist.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category", schema = "tasklist", catalog = "")
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class Category {
    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;
    private Collection<Task> tasksById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "completed_count", nullable = true)
    public Long getCompletedCount() {
        return completedCount;
    }


    @Basic
    @Column(name = "uncompleted_count", nullable = true)
    public Long getUncompletedCount() {
        return uncompletedCount;
    }




}
