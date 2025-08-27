package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = {"src/test/resources/features/users.feature", "src/test/resources/features/bookings.feature"},
    glue = {"com.testapi.steps", "com.testapi.steps.booker"}
)
public class CucumberTestSuite {}
