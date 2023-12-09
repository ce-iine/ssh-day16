package ssf.workshop16.workshop16.repo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonValue;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import ssf.workshop16.workshop16.models.Boardgame;
import ssf.workshop16.workshop16.services.BoardgameService;
import jakarta.json.JsonObject;

@Repository
public class BoardgameRepo {

    @Autowired
    @Qualifier("boardgameRedis") // from AppConfig if you name a bean, go back to that bean
    private RedisTemplate<String, Object> template;

    @Autowired
    BoardgameService boardgameService;

    Resource resource = new ClassPathResource("static/game.json");
    // Interface for a resource descriptor that abstracts from the actual type of
    // underlying resource, such as a file or class path resource.
    String hashKey = "allgames";
    List<Boardgame> gamesList = new ArrayList<Boardgame>();

    public JsonObject readJson() throws IOException { // hget (id) (gameid)

        InputStream is = resource.getInputStream();
        JsonReader jsonReader = Json.createReader(is);
        JsonArray jsonArray = jsonReader.readArray(); // data is stored as arrays
        // System.out.println(jsonArray);

        for (JsonValue jsonValue : jsonArray) { // for (JsonObject object: jsonArray.getValuesAs(JsonObject.class)){}
            JsonObject jsonObject = jsonValue.asJsonObject();
            Boardgame game = new Boardgame();
            boardgameService.setFields(jsonObject, game);
            gamesList.add(game);
            template.opsForHash().put(hashKey, game.getGid(), game); // .toJson().toString()
        }

        Integer insert_count = gamesList.size();

        JsonObject resp = Json.createObjectBuilder()
                .add("insert_count", insert_count.toString())
                .add("id", hashKey)
                .build();

        return resp;
    }

    public Object getBoardgame(String id) {
        if (template.opsForHash().get(hashKey, id) != null) { // correct to get from
            Object obj = template.opsForHash().get(hashKey, id);
            System.out.println(obj);
            return obj;
        } else {
            ResponseEntity.status(HttpStatusCode.valueOf(400));
        }

        return id;
    }

    public Object updateBoardgame(Boardgame payload, String id, Boolean upsert) {
        Integer update_count = 0;

        if (template.opsForHash().get(hashKey, id) != null) {
            System.out.println("INSIDE HEREE====================");

            Object getGame = template.opsForHash().get(hashKey, id);
            System.out.println(getGame);

            template.opsForHash().put(hashKey, payload.getGid(), payload);

            System.out.println(template.opsForHash().get(hashKey, id));

            update_count++;
        } else { // if upsert =true in request
            if (upsert) {
                template.opsForHash().putIfAbsent(hashKey, payload.getGid(), payload);
                update_count++;
            }
            if (upsert == null) {
                System.out.println("id does not exist and upsert is null");
                HttpStatusCode.valueOf(404);
            }
        }

        JsonObject resp = Json.createObjectBuilder()
                .add("update_count", update_count.toString())
                .add("id", hashKey)
                .build();

        return resp;
    }

}
