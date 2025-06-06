package main.java;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.*;
import static main.files.payload.*;

public class DynamicJsonTests {

    @DataProvider(name = "BooksData")
    public Object[][] getDataProvider() {
        return new Object[][] {{"ojfwty", "9363"}, {"cwetee", "4253"}, {"okmfet", "533"}};
    }

    @Test(dataProvider = "BooksData")
    public static void addBookTest(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String addBookResponse = given().log().all()
                    .header("Content-Type", "application/json")
                    .body(AddBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath addBookJson = ReusableMethods.rawToJson(addBookResponse);
        String id = addBookJson.get("ID");
        System.out.println(id);
    }

}
