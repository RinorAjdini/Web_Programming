package mk.ukim.finki.wp.jan2022.g1.service.impl;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.TaskCategory;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidTaskIdException;
import mk.ukim.finki.wp.jan2022.g1.repository.TaskRepository;
import mk.ukim.finki.wp.jan2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.jan2022.g1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService
{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);
    }

    @Override
    public Task create(String title, String description, TaskCategory category, List<Long> assignees, LocalDate dueDate) {
        List<User> users = userRepository.findAllById(assignees);

        Task task = new Task(title,description,category,users,dueDate);
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, String title, String description, TaskCategory category, List<Long> assignees) {
        List<User> users = userRepository.findAllById(assignees);

        Task task = this.findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setAssignees(users);

        return taskRepository.save(task);

    }

    @Override
    public Task delete(Long id) {
        Task task = this.findById(id);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public Task markDone(Long id) {
        Task task = this.findById(id);
        task.setDone(true);
       return taskRepository.save(task);
    }

    @Override
    public List<Task> filter(Long assigneeId, Integer lessThanDayBeforeDueDate) {
       if(assigneeId==null && lessThanDayBeforeDueDate==null)
       {
           return listAll();
       }
       else if(assigneeId==null)
       {
           return taskRepository.findAllByDueDateBefore(LocalDate.now().plusDays(lessThanDayBeforeDueDate));
       }
       else if(lessThanDayBeforeDueDate==null)
       {
           return taskRepository.findAllByAssigneesContaining(userService.findById(assigneeId));
       }
       else
       {
           return taskRepository.findAllByAssigneesContainingAndDueDateBefore(userService.findById(assigneeId),LocalDate.now().plusDays(lessThanDayBeforeDueDate));
       }
    }
}
