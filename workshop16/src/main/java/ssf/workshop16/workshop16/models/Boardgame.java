package ssf.workshop16.workshop16.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boardgame {
    
    String gid;
    String name;
    Integer year;
    Integer ranking;
    Integer users_rated;
    String url;
    String image;

    // public JsonObject toJson(){
    //     JsonObject builder = Json.createObjectBuilder()
    //         .add("gid", this.getGid())
    //         .add("name", this.getName())
    //         .add("year", this.getYear())
    //         .add("ranking", this.getRanking())
    //         .add("users_rated", this.getUsers_rated())
    //         .add("url", this.getUrl())
    //         .add("image", this.getImage())
    //         .build();

    //     return builder;
    // }    
}
