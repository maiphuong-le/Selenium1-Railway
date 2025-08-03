package dataTest;

import Constant.Constant;
import Model.Account;
import org.testng.annotations.DataProvider;

public class DataTests {
    @DataProvider(name = "validRegisterAccount")
    public static Object[][] validRegisterAccount() {
        return new Object[][]{
                {
                        new Account(
                                Constant.generateUniqueEmail(),
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PASSWORD,
                                Constant.VALID_PID
                        )
                }
        };
    }
    @DataProvider(name = "invalidConfirmPasswordAccount")
    public static Object[][] invalidConfirmPasswordAccount() {
        return new Object[][] {
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
        return new Object[][] {
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

}
