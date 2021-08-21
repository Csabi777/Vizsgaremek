import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PreferencesPage {
    WebDriver driver;

    public final By USERPROFILE = By.xpath("//*[@id=\"ooui-php-408\"]/span");
    public final By SIGNATUREINPUT = By.xpath("//*[@id=\"ooui-php-39\"]");
    public final By SAVEBUTTON = By.xpath("//*[@id=\"prefcontrol\"]/button");


    public PreferencesPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickUserProfile(){
        driver.findElement(USERPROFILE).click();

    }

    public void setSignature(String signature){
        driver.findElement(SIGNATUREINPUT).clear();
        driver.findElement(SIGNATUREINPUT).sendKeys(signature);
    }

    public void clickSaveButton(){
        driver.findElement(SAVEBUTTON).click();
    }

    public void deleteSignature(){
        driver.findElement(SIGNATUREINPUT).click();
        driver.findElement(SIGNATUREINPUT).clear();
    }

    public void deleteSignatureKeys(){
        driver.findElement(SIGNATUREINPUT).click();
        driver.findElement(SIGNATUREINPUT).sendKeys(Keys.CONTROL + "a");
        driver.findElement(SIGNATUREINPUT).sendKeys(Keys.DELETE);
    }


}
