package data;
import org.testng.annotations.DataProvider;
public class CheckoutDataProvider {

    @DataProvider(name = "checkoutValidationData")
    public static Object[][] checkoutValidationData() {

        return new Object[][]{
                {
                        "",
                        "Katoch",
                        "201310",
                        "Error: First Name is required"
                },

                {
                        "Payal",
                        "Katoch",
                        "",
                        "Error: Postal Code is required"
                }
        };
    }
}