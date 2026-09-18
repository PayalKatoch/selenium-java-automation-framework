package data;
import org.testng.annotations.DataProvider;
public class LoginDataProvider {

        @DataProvider(name = "invalidLoginData")
        public static Object[][] getInvalidLoginData() {
            return new Object[][]{
                    {
                            "locked_out_user",
                            "secret_sauce",
                            "Epic sadface: Sorry, this user has been locked out."
                    },
                    {
                            "invalid_user",
                            "wrong_password",
                            "Epic sadface: Username and password do not match any user in this service"
                    }
            };
        }
    }
