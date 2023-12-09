package ssf.workshop16.workshop16.services;

import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import ssf.workshop16.workshop16.models.Boardgame;

@Service
public class BoardgameService {

    public Boardgame setFields(JsonObject jsonObject, Boardgame game) {
        game.setGid(jsonObject.get("gid").toString());
        game.setName(jsonObject.get("name").toString());
        game.setYear(Integer.parseInt(jsonObject.get("year").toString()));
        game.setRanking(Integer.parseInt(jsonObject.get("ranking").toString()));
        game.setUsers_rated(Integer.parseInt(jsonObject.get("users_rated").toString()));
        game.setUrl(jsonObject.get("url").toString());
        game.setImage(jsonObject.get("image").toString());

        return game;
    }

}
