package dataTest;

import Constant.Constant;
import DataTypes.SeatType;
import DataTypes.Station;
import Model.Account;
import Model.Ticket;
import org.testng.annotations.DataProvider;

import java.time.LocalDate;

public class DataTests {

    private static Account createValidAccount() {
        return new Account(
                Constant.generateUniqueEmail(),
                Constant.VALID_PASSWORD,
                Constant.VALID_PASSWORD,
                Constant.VALID_PID
        );
    }

    @DataProvider(name = "validRegisterAccount")
    public static Object[][] validRegisterAccount() {
        return new Object[][]{
                {createValidAccount()}
        };
    }

    @DataProvider(name = "invalidConfirmPasswordAccount")
    public static Object[][] invalidConfirmPasswordAccount() {
        return new Object[][]{
                {
                        new Account(
                                Constant.generateUniqueEmail(),
                                Constant.VALID_PASSWORD,
                                "Different123",
                                Constant.VALID_PID
                        )
                }
        };
    }

    @DataProvider(name = "emptyPasswordAndPid")
    public static Object[][] emptyPasswordAndPid() {
        return new Object[][]{
                {
                        new Account(
                                "test" + System.currentTimeMillis() + "@example.com",
                                "",
                                "",
                                ""
                        )
                }
        };
    }

    @DataProvider(name = "registerAccount")
    public static Object[][] registerAccount() {
        return new Object[][]{
                {createValidAccount()}
        };
    }

    @DataProvider(name = "changePasswordData")
    public Object[][] changePasswordData() {
        Account account = new Account(
                "autotest" + System.currentTimeMillis() + "@mail.com",
                "ValidPassword123",
                "ValidPassword123",
                "1234567891011"
        );

        return new Object[][]{
                {account, "newPass123", "newPass123"},
                {account, "Password!1", "Password!1"}
        };
    }

    @DataProvider(name = "change_password_data")
    public Object[][] change_password_data() {
        return new Object[][]{
                {
                        new Account(
                                Constant.generateUniqueEmail(),
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PID
                        ),
                        "newPass123",
                        "newPass123"
                },
                {
                        new Account(
                                Constant.generateUniqueEmail(),
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PID
                        ),
                        "Password!1",
                        "Password!1"
                }
        };
    }


    // === TC12: Reset password with blank token ===
    @DataProvider(name = "resetPasswordBlankToken")
    public static Object[][] resetPasswordBlankToken() {
        String newPass = "NewPass" + System.currentTimeMillis();
        return new Object[][]{
                {createValidAccount(), newPass, newPass, ""}
        };
    }

    // === TC13: Reset password with mismatched confirm password ===
    @DataProvider(name = "resetPasswordMismatch")
    public static Object[][] resetPasswordMismatch() {
        String newPass = "NewPass" + System.currentTimeMillis();
        String mismatchPass = "Mismatch" + System.currentTimeMillis();
        String fakeToken = "VALID_FAKE_TOKEN"; // giả token hợp lệ để test
        return new Object[][]{
                {createValidAccount(), newPass, mismatchPass, fakeToken}
        };
    }

    @DataProvider(name = "bookOneTicketData")
    public Object[][] bookOneTicketData() {
        Account newAccount = new Account(
                "automation_user_" + System.currentTimeMillis() + "@example.com",
                "Password123!",
                "Password123!",
                "123456789"
        );

        Ticket ticket = new Ticket();
        ticket.setDepartDate(LocalDate.now().plusDays(7));
        ticket.setFrom(Station.SAI_GON);
        ticket.setTo(Station.NHA_TRANG);
        ticket.setSeatType(SeatType.SOFT_BED_AC);
        ticket.setTicketAmount(1);

        return new Object[][]{
                {newAccount, ticket}
        };
    }

    @DataProvider(name = "bookTicketFromHueToSaiGon")
    public Object[][] bookTicketFromHueToSaiGon() {
        Account newAccount = new Account(
                "automation_user_" + System.currentTimeMillis() + "@example.com",
                "Password123!",
                "Password123!",
                "123456789"
        );

        return new Object[][]{
                {newAccount, Station.HUE, Station.SAI_GON}
        };
    }

    @DataProvider(name = "ticket_data_cancel")
    public Object[][] ticket_data_cancel() {
        Account newAccount = new Account(
                "automation_user_" + System.currentTimeMillis() + "@example.com",
                "Password123!",
                "Password123!",
                "123456789"
        );

        Ticket ticket = new Ticket();
        ticket.setDepartDate(LocalDate.now().plusDays(7));
        ticket.setFrom(Station.HUE);
        ticket.setTo(Station.SAI_GON);
        ticket.setSeatType(SeatType.SOFT_BED_AC);
        ticket.setTicketAmount(1);

        return new Object[][]{
                {newAccount, ticket}
        };
    }


}
