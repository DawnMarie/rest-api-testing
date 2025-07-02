package main.java;

import io.restassured.path.json.JsonPath;
import main.files.payload;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComplexJsonParse {

    @Test
    public void complexJsonValidationTest() {
        JsonPath mockedResponse = new JsonPath(payload.CoursePrice());

        int courseCount = mockedResponse.getInt("courses.size()");
        System.out.println(courseCount);

        int purchaseAmount = mockedResponse.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        String courseTitle = mockedResponse.getString("courses[0].title");
        System.out.println(courseTitle);

        int summedTotalSales = 0;
        for (int i=0;i<courseCount;i++) {
            courseTitle = mockedResponse.get("courses[" + i + "].title");
            int coursePrice = mockedResponse.get("courses[" + i + "].price");
            int courseCopies = mockedResponse.get("courses[" + i + "].copies");
            int courseSales = coursePrice * courseCopies;
            summedTotalSales = summedTotalSales + courseSales;

            System.out.println(courseTitle);
            System.out.println(coursePrice);
        }

        for (int i=0;i<courseCount;i++) {
            if (courseTitle.equalsIgnoreCase("RPA")) {
                int copies = mockedResponse.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }

        Assert.assertEquals(summedTotalSales, purchaseAmount);
    }
}
