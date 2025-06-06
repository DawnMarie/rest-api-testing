package main.java;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    private static final String KEY = "qaclick123";

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addPlaceResponse = given().log().all()
                    .queryParam("key", KEY)
                    .header("Content-Type", "application/json")
                    .body(new String(Files.readAllBytes(Paths.get(
                            "C:\\Users\\DDunn\\IdeaProjects\\rest-api-testing\\src\\main\\files\\addPlace.json"
                    )))).
                when()
                    .post("maps/api/place/add/json").
                then().log().all().assertThat()
                    .statusCode(200)
                    .body("scope", equalTo("APP"))
                    .header("server", "Apache/2.4.18 (Ubuntu)")
                    .extract().response().asString();
        System.out.println(addPlaceResponse);

        JsonPath addPlaceJson = ReusableMethods.rawToJson(addPlaceResponse);
        String placeId = addPlaceJson.getString("place_id");
        System.out.println(placeId);

        String newAddress = "Summer Walk, Africa";

        given().log().all()
                .queryParam("key", KEY)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"place_id\":\"" + placeId + ",\n" +
                        "  \"address\":\"" + newAddress + ",\n" +
                        "  \"key\":" + KEY + "\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat()
                    .statusCode(200)
                    .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all()
                .queryParam("key", KEY).queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat()
                    .statusCode(200)
                .extract().response().asString();
        JsonPath getPlaceJson = ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress = getPlaceJson.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, newAddress);
    }
}
