import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {    //*[@id="p-lang"]/button
    WebDriver driver;

    public final By LOGINBUTTON = By.xpath("//*[@id='pt-login']/a");
    public final By USERNAMEINPUT = By.xpath("//*[@id='wpName1']");
    public final By PASSWORDINPUT = By.xpath("//*[@id='wpPassword1']");
    public final By BLUELOGINBUTTON = By.xpath("//*[@id='wpLoginAttempt']");
    public final By LOGOUTBUTTON = By.xpath("//*[@id=\"pt-logout\"]/a");
    public final By PRIVACYANDPOLICYLINK = By.xpath("//*[@id=\"footer-places-privacy\"]/a");
    public final By THEARTSLINK = By.xpath("//*[@id=\"mp-portals\"]/li[1]/a");
    public final By PREFERENCES = By.xpath("//*[@id=\"pt-preferences\"]/a");
    public final By SETTINGSBUTTON = By.xpath("//*[@id=\"p-lang\"]/button");





    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
    }

    public void clickLoginButton() {
        driver.findElement(LOGINBUTTON).click();
    }

    public void sendUsername(String username) {
        driver.findElement(USERNAMEINPUT).sendKeys(username);
    }

    public void sendPassword(String password) {
        driver.findElement(PASSWORDINPUT).sendKeys(password);
    }

    public void clickBlueLoginButton() {
        driver.findElement(BLUELOGINBUTTON).click();
    }

    public String getLogoutText(){
        String text = driver.findElement(LOGOUTBUTTON).getText();
        return text;
    }
    public void clickLogout(){
        driver.findElement(LOGOUTBUTTON);
    }

    public void clickPrivacyAndPolicy() {
        driver.findElement(PRIVACYANDPOLICYLINK).click();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()");
    }

    public void clickTheArtsLink() {
        driver.findElement(THEARTSLINK).click();
    }

    public void clickPreferences(){
        driver.findElement(PREFERENCES).click();
    }

    public void clickSettings(){
        driver.findElement(SETTINGSBUTTON).click();
    }
}
