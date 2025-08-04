package main.java;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    public static void main(String[] args) {

        String response = given().log().all()
                .formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        JsonPath responseJson = new JsonPath(response);
        String accessToken = responseJson.get("access_token");

        response = given().log().all()
                .queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

        System.out.println(response);
    }
}
