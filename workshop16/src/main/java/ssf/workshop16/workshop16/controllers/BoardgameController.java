package ssf.workshop16.workshop16.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import ssf.workshop16.workshop16.models.Boardgame;
import ssf.workshop16.workshop16.repo.BoardgameRepo;

@RestController
@RequestMapping("/api")
public class BoardgameController {

    @Autowired
    BoardgameRepo boardgameRepo;

    @PostMapping(path = "/boardgame", consumes = MediaType.APPLICATION_JSON_VALUE) // content type match consumes
    public ResponseEntity<JsonObject> docInsert() throws IOException {
        JsonObject response = boardgameRepo.readJson();

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(response);
    }

    @GetMapping("/boardgame/{id}") // http://localhost:8080/api/boardgame/272409
    public ResponseEntity<Object> getBoardgame(@PathVariable String id)
            throws JsonMappingException, JsonProcessingException {

        Object boardgame = boardgameRepo.getBoardgame(id);

        return ResponseEntity.ok().body(boardgame);
    }

    @PutMapping(path = "/boardgame/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateData(@PathVariable String id, @RequestBody Boardgame payload,
            @RequestParam(value = "upsert", required = false) Boolean upsert) {
        // http://localhost:8080/api/boardgame/272409?upsert=false
        // http://localhost:8080/api/boardgame/(newid)?upsert=
        // http://localhost:8080/api/boardgame/(newid)?upsert=true

        System.out.printf(">>> @RequestBody: %s\n", payload);
        if (upsert == null) { // if not specifed in request
            upsert = false;
        } 

        Object response = boardgameRepo.updateBoardgame(payload, id, upsert);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
