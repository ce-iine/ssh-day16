package ssh.iss.day16work.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import ssh.iss.day16work.models.Task;

@Service
public class TaskService {

    public Task save(Task task) {
        task.setId(UUID.randomUUID().toString());
        task.setCreateTime((new Date().getTime()));

        return task;
    }

}
