package mk.ukim.finki.wp.jan2022.g1.web;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.TaskCategory;

import mk.ukim.finki.wp.jan2022.g1.service.TaskService;
import mk.ukim.finki.wp.jan2022.g1.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

public class TaskController {


    /**
     * This method should use the "list.html" template to display all entities.
     * The method should be mapped on paths '/' and '/tasks'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "list.html".
     */

    public String showList(Integer lessThanDayBeforeDueDate,
                           Long assigneeId)

    {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/tasks/add'.
     *
     * @return The view "form.html".
     */

    public String showAdd()
    {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/tasks/[id]/edit'.
     *
     * @return The view "form.html".
     */

    public String showEdit(Long id)
    {
        return "";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/tasks'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */

    public String create(String title,
                         String description,
                         TaskCategory category,
                         List<Long> assignees,
                         String dueDate)
    {
        return "";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/tasks/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/tasks/{id}")
    public String update(Long id,
                         String title,
                         String description,
                         TaskCategory category,
                         List<Long> assignees)
    {
        return "";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/tasks/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        return "";
    }

    /**
     * This method should mark the task that has the appropriate identifier as done.
     * The method should be mapped on path '/tasks/[id]/mark_done'.
     * After the its execution, the list of entities should be displayed.
     *
     * @return The view "list.html".
     */
    public String markDone( Long id) {
        return "";
    }
}
