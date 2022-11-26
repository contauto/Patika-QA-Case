package Base;

import org.openqa.selenium.*;

public class Func {

    public static void start(WebDriver driver){
        driver.get("https://www.e-bebek.com/");
        driver.manage().window().setSize(new Dimension(1536, 824));
    }


    public static void sleep(int sec) {
        try {
            for (int i = 0; i < sec; i++) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            //
        }
    }




}