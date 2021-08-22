import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivacyAndPolicyPage {
    WebDriver driver;


    public final By YOURRIGHTSTEXT = By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[123]");
    public final By YOURRIGHTS = By.xpath("//*[@id=\"Your_rights\"]");
    public final By OLDPOLICY = By.xpath("//*[@id=\"mw-content-text\"]/div[1]/ul/li[1]/b/a");
    public final By PREVIOUSPOLICY= By.xpath("//*[@id=\"mw-revision-nav\"]/a[2]");
    public final By REVISIONINFO= By.xpath("//*[@id=\"mw-revision-info\"]");

    public PrivacyAndPolicyPage(WebDriver driver) {this.driver = driver;

    }


    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()");
    }


    public String getPrivacyRights() {
        String title = driver.findElement(YOURRIGHTS).getText();
        return title;
    }


    public String getPrivacyText() {
        String text = driver.findElement(YOURRIGHTSTEXT).getText();
        return text;
    }

    public void clickOldPolicy(){
        driver.findElement(OLDPOLICY).click();
    }

    public void clickPrevPolicy(){
        driver.findElement(PREVIOUSPOLICY).click();
    }

    public String getRevisionInfo(){
        String info = driver.findElement(REVISIONINFO).getText();
        return info;
    }
}
