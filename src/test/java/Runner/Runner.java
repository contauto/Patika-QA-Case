package Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue= {"Steps"},
        plugin = {"pretty", "html:target/report/cucumber.html", "json:target/report/cucumber.json", "junit:target/report/cucumber.xml"},
        monochrome = true
)
public class Runner {
}
