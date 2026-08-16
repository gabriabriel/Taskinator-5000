package com.taskinator5000_api.service;

import com.taskinator5000_api.entity.Task;
import com.taskinator5000_api.enums.TaskStatus;
import com.taskinator5000_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {
    private final TaskRepository taskRepository;
    private final EmailService emailService;

    @Value("${taskinator.email}")
    private String userEmail;

    public ReminderService(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 60000)
    public void sendPendingReminders(){

        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = taskRepository.findTasksWithPendingReminders(now, TaskStatus.COMPLETED);

        for(Task task : tasks){
            emailService.sendReminderEmail(task, userEmail);

            task.setReminderSent(true);
            taskRepository.save(task);
        }
    }


}
