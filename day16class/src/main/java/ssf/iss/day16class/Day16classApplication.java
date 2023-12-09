package ssf.iss.day16class;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.JsonArray;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@SpringBootApplication
public class Day16classApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Day16classApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// get a JSONOBJECT builder and build jsonobject

		// JsonObjectBuilder builder = Json.createObjectBuilder();
		// builder.add("firstName", "celine");
		// builder.add("lastName", "ng");
		// builder.add("age", 24);
		// builder.add("married", false);
		// builder.add("height", 5.7);

		// JsonObject fred = Json.createObjectBuilder()
		// 		.add("firstName", "fred")
		// 		.add("lastName", "flintstone")
		// 		.add("age", 58)
		// 		.add("married", true)
		// 		.add("height", 5.9)
		// 		.build();

		// JsonObject wilma = Json.createObjectBuilder()
		// 		.add("firstName", "wilma")
		// 		.add("lastName", "flintstone")
		// 		.add("age", 58)
		// 		.add("married", true)
		// 		.add("height", 5.9)
		// 		.add("spouse", fred) // becomes array
		// 		.build();

		// JsonArray flintstones = Json.createArrayBuilder()
		// 	.add(fred)
		// 	.add(wilma)
		// 	.build();

		// // create json object
		// JsonObject celine = builder.build();

		// System.out.printf("celine:%s\n", celine.toString());
		// System.out.println("--------------------------");
		// System.out.printf("flintstones:%s\n", flintstones.toString());

		// String name = wilma.getString("firstName", "not set");
		// boolean married = wilma.getBoolean("married");
		// Integer age = wilma.getInt("age", -1);
		// float height = (float) wilma.getJsonNumber("height").doubleValue();
		// JsonObject spouse = wilma.getJsonObject("spouse");

		// System.out.println("====================");

		// for (int i; i<flintstones.size(); i++){
		// 	JsonObject o =flintstones.getJsonObject(i);
		// 	System.out.printf("%d>>>%s\n",i,o);
		// }

		// System.out.println("");

	}

}
