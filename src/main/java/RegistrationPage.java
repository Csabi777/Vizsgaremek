import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    public final By WPNAME2 = By.xpath("//*[@id=\"wpName2\"]");
    public final By WPPASS2 = By.xpath("//*[@id=\"wpPassword2\"]");
    public final By REPASS = By.xpath("//*[@id=\"wpRetype\"]");
    public final By CAPCHAINPUT = By.xpath("//*[@id=\"mw-input-captchaWord\"]");
    public final By CREATEACCBUTTON = By.xpath("//*[@id=\"wpCreateaccount\"]");


    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    public void addName(String name){
        driver.findElement(WPNAME2).sendKeys(name);
    }

    public void addPassword(String password){
        driver.findElement(WPPASS2).sendKeys(password);
    }

    public void confirmPassword(String password){
        driver.findElement(REPASS).sendKeys(password);
    }

    public void writeCapcha(String captext){
        driver.findElement(CAPCHAINPUT).sendKeys(captext);

    }

    public void clickCreateAcc(){
        driver.findElement(CREATEACCBUTTON).click();
    }
}
