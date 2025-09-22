package Constant;

import java.time.format.DateTimeFormatter;

public class Constant {

    public static final String RAILWAY_URL = "http://railwayb1.somee.com/";
    public static final String USERNAME = "phuongle@agest.com";
    public static final String BLANK_USERNAME = "";
    public static final String PASSWORD = "!hT7$kLp9Z@3wFqR";

    public static final String VALID_PASSWORD = "Valid12345";
    public static final String INVALID_PASSWORD = "invalidPassword123";
    public static final String VALID_PID = "1234567890";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public static String generateUniqueEmail() {
        return "user" + System.currentTimeMillis() + "@mail.com";
    }
}
