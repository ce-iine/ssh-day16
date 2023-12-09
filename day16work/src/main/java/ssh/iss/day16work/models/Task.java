package ssh.iss.day16work.models;

import java.io.Reader;
import java.io.StringReader;
import java.util.Optional;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Task {

    private String id;
    private Long createTime;
    private String name;
    private String description;
    private Integer priority;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public static Optional<Task> toTask(String payload){

        Reader reader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(reader);

        // return either object or array
        JsonObject data = jsonReader.readObject();

        Task task = new Task();
        task.setName(data.getString("name",""));
        task.setDescription(data.getString("description", ""));
        task.setPriority(Integer.parseInt(data.getString("priority","-1")));

        return Optional.of(task);
    }
    
}
