package mk.ukim.finki.wp.jan2022.g1.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


public class Task {

    public Task() {
    }

    public Task(String title, String description, TaskCategory category, List<User> assignees, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.assignees = assignees;
        this.dueDate = dueDate;
    }


    private Long id;

    private LocalDate dueDate;

    private String title;

    private String description;

    private TaskCategory category;

    private List<User> assignees;

    private Boolean done = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public List<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<User> assignees) {
        this.assignees = assignees;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
