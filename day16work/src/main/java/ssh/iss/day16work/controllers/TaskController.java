package ssh.iss.day16work.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssh.iss.day16work.models.Task;
import ssh.iss.day16work.services.TaskService;

@Controller
@RequestMapping(path = "/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskSvc;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptTask(@RequestBody String payload) {

        //check if JSON payload is correct
        Optional<Task> opt = Task.toTask(payload);

        JsonObject response;

        if (opt.isEmpty()) {
            response = Json.createObjectBuilder()
                .add("error", "incorrect task payload")
                .build();
            return ResponseEntity.badRequest().body(response.toString());
        }

        Task task = opt.get();
        task =taskSvc.save(task);

        response = Json.createObjectBuilder()
            .add("id", task.getId())
            .add("createTime", task.getCreateTime())
            .build();

        return ResponseEntity.ok(response.toString());

    }

}
