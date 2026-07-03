package org.todo.todorails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.todo.todorails.model.Task;
import org.todo.todorails.model.User;
import org.todo.todorails.repository.TaskRepository;
import org.todo.todorails.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Method to save a new task
    public Task saveTask(Task task) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        task.setUser(user);
        task.setDateAdded(LocalDate.now());

        return taskRepository.save(task);
    }

    // Get today's tasks for user
    public List<Task> getTodayTasksForCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        LocalDate currentDate = LocalDate.now();

        List<Task> taskListForToday = new ArrayList<>();

        // TODO 12 FIX
        taskListForToday = taskRepository.findByUserAndDueDateAndCompleted(user, currentDate, false);

        return taskListForToday;
    }

    // Method to get all tasks for current user
    public List<Task> getAllTasksForCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        return taskRepository.findByUser(user);
    }

    // Method to mark a task as done
    public boolean markTaskAsDone(Long taskId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        Task task = taskRepository.findByUserAndId(user, taskId);

        if (task != null && !task.isCompleted()) {

            // TODO 16 FIX
            task.setCompleted(true);
            taskRepository.save(task);
            return true;
        }

        return false;
    }

    // Method to get a task which is not done
    public Task getTaskById(Long taskId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        Task task = taskRepository.findByUserAndId(user, taskId);

        if (task != null && !task.isCompleted()) {
            return task;
        }

        return null;
    }

    // Method to get a task without checking done flag
    public Task getTaskByIdAny(Long taskId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        Task task = taskRepository.findByUserAndId(user, taskId);

        if (task != null) {
            return task;
        }

        return null;
    }

    // Method to update an existing task
    public boolean updateTaskForUser(Task task) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        Task taskInDb = taskRepository.getById(task.getId());

        if (user != null && !user.getUsername().equals(taskInDb.getUser().getUsername())) {
            return false;
        }

        Task existingTask = taskRepository.findByUserAndId(user, task.getId());

        if (existingTask != null) {

            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setType(task.getType());

            existingTask.setDateAdded(LocalDate.now());

            Task taskUpdated = taskRepository.save(existingTask);

            if (taskUpdated != null) {
                return true;
            }
        }

        return false;
    }

    // Method to delete a task
    public boolean deleteTask(Task task) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username);

        Task taskInDb = taskRepository.getById(task.getId());

        if (user != null && !user.getUsername().equals(taskInDb.getUser().getUsername())) {
            return false;
        }

        // FIX: use logged-in user instead of task.getUser()
        Task existingTask = taskRepository.findByUserAndId(user, task.getId());

        if (existingTask != null) {
            taskRepository.delete(existingTask);
            return true;
        }

        return false;
    }

    public int countByCompleted(boolean completedStatus) {

        // TODO 20 FIX
        return taskRepository.countByCompleted(completedStatus);
    }
}