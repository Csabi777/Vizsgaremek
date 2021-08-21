import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TheArtsPage {
    WebDriver driver;

    public final By THEARTSHEADLINE = By.xpath("//*[@id=\"portals-browsebar\"]/dl/dd");


    public TheArtsPage(WebDriver driver){ this.driver = driver;

    }

    public List<String> menuLister(){
        List<WebElement> bartext = driver.findElements(THEARTSHEADLINE);
        List<String> result = new ArrayList<>();
        for (WebElement i: bartext) {
            result.add(i.getText());
        }
        return result;
    }

}





