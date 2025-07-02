package main.java;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.io.File;
import java.util.Arrays;

public class BugTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy-team.atlassian.net";

        String createIssueResponse = given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic <Base64_encoded_string>")
                .body("{\n"
                        + "    \"fields\": {\n"
                        + "       \"project\":\n"
                        + "       {\n"
                        + "          \"key\": \"SCRUM\"\n"
                        + "       },\n"
                        + "       \"summary\": \"Website items are not working- automation Rest Assured\",\n"
                        + "       \"issuetype\": {\n"
                        + "          \"name\": \"Bug\"\n"
                        + "       }\n"
                        + "   }\n"
                        + "}")
                .post("rest/api/3/issue")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract().response().asString();

        JsonPath createIssueJson = new JsonPath(createIssueResponse);
        String issueId = createIssueJson.getString("id");
        System.out.println(issueId);

        given().log().all()
                .header("X-Atlassian-Token", "no-check")
                .header("Authorization", "Basic <Base64_encoded_string>")
                .multiPart("file", new File("./files/screenshot.png"))
                .pathParam("key", issueId)
                .post("rest/api/3/issue/{key}/attachments")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
