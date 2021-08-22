import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LanguageSettingPage {
    WebDriver driver;

    public final By FONTSBUTTON = By.xpath("//*[@id=\"uls-display-settings-fonts-tab\"]");
    public final By CHECKBOX = By.xpath("//*[@id=\"webfonts-enable-checkbox\"]");
    public final By FONTSSELECTOR = By.xpath("//*[@id=\"content-font-selector\"]");
    public final By APPLYSETTINGBUTTON = By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]");

    public LanguageSettingPage(WebDriver driver){
        this.driver = driver;

    }

    public void clickFontButton(){
        driver.findElement(FONTSBUTTON).click();

    }

    public void checkCheckbox(){
        if (!driver.findElement(CHECKBOX).isSelected()){
            driver.findElement(CHECKBOX).click();
        }
    }

    public void setFonts(String fonts){
        Select select = new Select(driver.findElement(FONTSSELECTOR));
        select.selectByVisibleText(fonts);
        driver.findElement(APPLYSETTINGBUTTON).click();
    }
}
