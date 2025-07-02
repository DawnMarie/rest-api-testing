package main.files;

public class payload {

    public static String AddPlace() {
        return "{"
                + "\"location\": {"
                    + "\"lat\": -38.383494, "
                    + "\"lng\": 33.427362\"}, "
                + "\"accuracy\": 50, "
                + "\"name\": \"Frontline house\", "
                + "\"phone_number\": \"(+91) 983 893 3937\","
                + "\"address\": \"29, side layout, cohen 09\","
                + "\"types\": [\"shoe park\", \"shop\"],"
                + "\"website\": \"http://google.com\","
                + "\"language\": \"French-IN\""
                + "}";
    }

    public static String AddBook(String isbn, String aisle) {
        return "{"
                + "\"name\":\"Learn Appium Automation with Java\", "
                + "\"isbn\":" + isbn + ", "
                + "\"aisle\":" + aisle + ", "
                + "\"author\":\"John foe\""
                + "}";

    }

    public static String CoursePrice() {
        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}\n";
    }
}
