package messages;

public class Message {
    // Success messages
    public static final String REGISTRATION_SUCCESS = "Registration Confirmed! You can now log in to the site.";
    public static final String WELCOME_MESSAGE = "Welcome ";
    public static final String WELCOME_MESSAGE_NOT_DISPLAYED = "Welcome message is not displayed as expected";

    // Error messages
    public static final String LOGIN_ERROR = "There was a problem with your login and/or errors exist in your form.";
    public static final String ERROR_USERNAME_BLANK = "Error message is not displayed as expected when username is blank";
    public static final String ERROR_PASSWORD_INVALID = "Error message is not displayed as expected when password is invalid";
    public static final String GENERAL_LOGIN_ERROR = "General login error message should appear";
    public static final String ERROR_MESSAGE_NOT_DISPLAYED = "Error message is not displayed as expected";
    public static final String FORM_ERROR = "There're errors in the form. Please correct the errors and try again.";
    public static final String INVALID_PASSWORD_LENGTH = "Invalid password length";
    public static final String INVALID_ID_LENGTH = "Invalid ID length";

    // Warning messages
    public static final String WARNING_LOGIN_ATTEMPTS = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
    public static final String WARNING_LOGIN_ATTEMPTS_NOT_DISPLAYED = "Warning message for multiple failed login attempts is not displayed as expected";

    // Navigation messages
    public static final String NOT_NAVIGATED_TO_CHANGE_PASSWORD = "User is not navigated to Change password page";
    public static final String NOT_NAVIGATED_TO_MY_TICKET = "User is not navigated to My ticket page";
    public static final String MY_TICKET_TAB_NOT_DISPLAYED = "\"My ticket\" tab is not displayed after login";
    public static final String CHANGE_PASSWORD_TAB_NOT_DISPLAYED = "\"Change password\" tab is not displayed after login";
    public static final String LOGOUT_TAB_NOT_DISPLAYED = "\"Logout\" tab is not displayed after login";
}