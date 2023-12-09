package ssf.iss.day16class.controllers;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController //return JSON Accept: application/json
@RequestMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeRestController {

    //return as json object and ask for json

    @GetMapping()
    public ResponseEntity<String> getTimeAsJson(){
        JsonObject resp = Json.createObjectBuilder()
            .add("time", (new Date()).toString())
            .build();

        return ResponseEntity.ok(resp.toString());
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE) //content type match consumes
    public ResponseEntity<String> postTimeAsJson(@RequestBody String payload){ //json dont have request param
        //convert string to json object
        //take string and parse to json reader
        Reader reader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(reader);

        //return either object or array 
        JsonObject data =jsonReader.readObject();
        System.out.printf(">>>>> data = %s\n", data.toString());

        //return smth (example)
        JsonObject resp = Json.createObjectBuilder()
            .add("status", "modified")
            .add("adjustment", data.getInt("adjustment"))
            .build();

        return ResponseEntity.status(201) //accepted is 201
            .header("my header", "header example")
            .body(resp.toString());
    }


    
}
