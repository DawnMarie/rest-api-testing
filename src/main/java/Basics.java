package main.java;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static main.files.payload.*;


public class Basics {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addPlaceResponse = given().log().all()
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(AddPlace()).
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
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"place_id\":\"" + placeId + ",\n" +
                        "  \"address\":\"" + newAddress + ",\n" +
                        "  \"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat()
                    .statusCode(200)
                    .body("msg", equalTo("Address successfully updated"));

        String getPlaceResponse = given().log().all()
                .queryParam("key", "qaclick123").queryParam("place_id", placeId)
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
